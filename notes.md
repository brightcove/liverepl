# Architecture

- External (run from terminal):
    - Attach agent to target JVM and provide basic I/O shuttling
    - Requires only tools.jar
- Internal (run as agent in target JVM):
    - Agent:
        - Sets up classloaders just long enough to launch a REPL thread, then exits
        - Uses port established and discarded previously by external code
        - Requires discovery code
    - REPL
        - Open a socket and start a REPL thread.
        - Requires Clojure

# Control flow

- shell script
    - finds tools.jar
    - Run client.Main from agent jar with args: clojure jar path,
      agent jar path, server jar path, other args
- client.Main (external)
    - (Requires tools.jar to talk to VM)
    - Ask target VM to load agent with args port, clojure jar path,
      server jar path, classloader ID
        - Passes "L" for classloader ID (to list classloaders) if none
          specified
    - (loadAgent waits for agent to complete)
    - Start client.Console (needed for listing classloaders not just
      REPL)
- client.Console
    - Starts two threads:
        - ConsoleHandler reads keyboard input and writes to target
          JVM's socket
        - SocketHandler reads responses from target JVM and writes to
          stdout
    - Waits for socket to close
- target JVM
    - Loads agent jar
    - calls Agent-Class (agent.Agent) agentmain method
- agent.Agent
    - If just listing classloaders, do that and exit.
        - Printing classloader info involves opening the socket on the
          port, waiting for a connection, and dumping the classloader
          info to the socket. This mirrors the REPL code.
    - Load clojure (if needed) and server
        - Constructs URLs and replaces thread's context classloader
          with a URLClassLoader that will delegate to the original
          one.
        - Discovery of classloader by ID
    - start REPL
        - loads server.Repl
        - calls its main method with LOCALHOST, port, and instrumentation
        - dynamically loads server.repl Clojure file
    - server.repl launches clojure.main/repl in a thread
        - Also launches killer thread to close the socket if the REPL
          failed to launch after 10 seconds.
    - returns, handing control back to client.Main


# Problems

- Does Main open a port that just anyone on the machine could talk to?
    - Looks like the socket is only .accept'd once, so an attacker has
      to use that small window of time. Still not good enough. (An
      attacker could make this invisible -- get to port first, kill
      REPL, disconnect socket, and restart REPL thread on port again.
- getFreePort isn't a guaranteed way to get a port, right?
- Only supports loading two jars: Clojure and server (could use
  colon-delimited list)
- Creation of var jvm.agent/instrumentation (introduced
  5086113ead69d470dba4e33d0249ffa325c8e15f) is not done for any
  documented reason.
- Does having a LiveREPL open interfere with normal JVM shutdown?
  (Does it create an non-daemon threads?)
