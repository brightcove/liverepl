#!/bin/bash
# Starter script for Clojure liverepl

[ -z "$JDK_HOME" ] && JDK_HOME=/usr/lib/jvm/default-java
LIVEREPL_HOME="$(cd -P -- "$(dirname -- "$0")" && pwd -P)"

MAIN=net.djpowell.liverepl.client.Main
CLIENT_JAR="$LIVEREPL_HOME/client/target/liverepl-client-standalone.jar"
AGENT_JAR="$LIVEREPL_HOME/agent/target/liverepl-agent.jar"
SERVER_JAR="$LIVEREPL_HOME/server/target/liverepl-server-standalone.jar"
EXTRA_JARS="$LIVEREPL_HOME/jars/"

if [ "Darwin" = "`uname -s`" ]; then
    CLASSPATH="${CLASSPATH}${JAVA_HOME}/bundle/Classes/classes.jar"
elif [ ! -f "$JDK_HOME/lib/tools.jar" ]; then
    echo 'Unable to find $JDK_HOME/lib/tools.jar'
    echo "Please set the JDK_HOME environment variable to the location of your JDK."
    exit 1
else
    CLASSPATH="${CLASSPATH}${JDK_HOME}/lib/tools.jar"
fi

if [ "$TERM" != "dumb" ]; then
    if which rlwrap >/dev/null ; then
        echo "Found rlwrap"
        breakchars="(){}[],^%$#@\"\";:''|\\"
        WRAP="exec rlwrap --remember -c -b \"$breakchars\" "
    fi
fi

CLASSPATH="$CLASSPATH:$CLIENT_JAR:$AGENT_JAR"

${WRAP}java -cp $CLASSPATH $MAIN "$AGENT_JAR" "$SERVER_JAR" "$EXTRA_JARS" "$@"



