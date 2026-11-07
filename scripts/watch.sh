#!/bin/bash
# Start the shadow-cljs watch server for WordMaster.
#
# NOTE: plain `npx shadow-cljs watch app` does NOT work in this VM:
# shadow-cljs resolves its Maven dependencies through an old wagon
# that cannot use the egress proxy (it tries a direct TLS connection
# to repo1.maven.org and fails with "Unsupported or unrecognized SSL
# message"). The dependencies themselves are all cached in
# ~/.m2/repository (populated by scripts/resolve_maven.py, which
# downloads via Python urllib through the proxy), so this script
# runs the compiler directly with a hand-built classpath instead.
# It is the exact equivalent of `npx shadow-cljs watch app` once
# the classpath is supplied.
#
# Usage: scripts/watch.sh            (watch + dev server on :3000)
#        scripts/watch.sh release app (one-off release build)
set -euo pipefail
cd "$(dirname "$0")/.."
export JAVA_HOME=/home/hatch/workspace/tools/jdk-21.0.13+9
export PATH="$JAVA_HOME/bin:$PATH"
if [ ! -f /tmp/wordmaster-cp.txt ]; then
  python3 scripts/classpath.py
fi
CP="src:test:$(cat /tmp/wordmaster-cp.txt)"
exec java -cp "$CP" clojure.main -m shadow.cljs.devtools.cli "$@"
