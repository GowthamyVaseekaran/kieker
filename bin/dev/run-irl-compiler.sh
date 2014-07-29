#!/bin/bash

BINDIR=$(cd "$(dirname "$0")"; pwd)

CLASSPATH=$(ls "${BINDIR}/lib/"*.jar | tr "\n" ":")
CLASSPATH="$CLASSPATH:${BINDIR}/target/de.cau.cs.se.instrumentation.rl.cli-1.0.0-SNAPSHOT.jar"

java -cp "${CLASSPATH}" de.cau.cs.se.instrumentation.rl.cli.CLICompilerMain "$@"

# end