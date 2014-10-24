LiveRepl
========

Start a Clojure REPL connected to any running Java or Clojure process
without needing the process to be setup in any special way beforehand.

Now supports connecting to Tomcat web applications.

You can use the repl to run code, inspect variables, and
redefine Clojure functions.

Build
-----

To build:

```
(cd client; lein uberjar) && (cd agent; lein jar) && (cd server; lein uberjar)
```

This produces jar files in the following locations:

```
client/target/liverepl-client-standalone.jar
agent/target/liverepl-agent.jar
server/target/liverepl-server-standalone.jar
```

Operation
---------

Call liverepl.sh as follows:

```bash
export JDK_HOME=/usr/lib/jvm/java-7-oracle
./liverepl.sh
```

To see a list of running Java processes on the system, and their
process ids, enter:

```bash
liverepl.sh
```

To see the available ClassLoaders for a specific process, enter:

```bash
liverepl.sh <pid>
```

where the pid is the process id for the process, obtained in the step
above.

To connect a repl to the process, enter:

```bash
liverepl.sh <pid> <classloader-id>
```

where the pid is the process id for the process and the classloader-id
was obtained in the step above.

If you aren't sure which ClassLoader to use, try `0`, which will
always be the System ClassLoader.

If the directory `./jars/` is present in the same location as
liverepl.sh, any jars inside will be loaded and made available inside
the target JVM. They must be at the top level inside the directory and
have a name ending in `.jar`.

License and credits
-------------------

This software is distributed under the MIT licence.

http://github.com/djpowell/liverepl

Original author: David Powell <djpowell@djpowell.net> 2009-10-18

Other contributions by:

- Kurt Harriger
- Kevin A. Archie
- Brightcove, Inc. (authors Chris Jeris, Tim McCormack)
