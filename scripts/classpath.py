#!/usr/bin/env python3
"""Regenerate /tmp/wordmaster-cp.txt: the Java classpath for running
shadow-cljs directly (see scripts/watch.sh). Reads the artifact list
from the last scripts/resolve_maven.py run (/tmp/wordmaster-resolve.log
or, if absent, re-runs the resolver) and maps each artifact to its
jar in ~/.m2/repository. Plain jar for everything, except
thheller:shadow-cljs which needs its -aot jar."""
import pathlib
import re
import subprocess
import sys

LOG = pathlib.Path("/tmp/wordmaster-resolve.log")
OUT = pathlib.Path("/tmp/wordmaster-cp.txt")
M2 = pathlib.Path.home() / ".m2" / "repository"


def main():
    if not LOG.exists():
        subprocess.run(
            [sys.executable, "scripts/resolve_maven.py"],
            check=True,
            stdout=open(LOG, "w"),
            stderr=subprocess.STDOUT,
        )
    arts = []
    for line in LOG.read_text().splitlines():
        m = re.match(r"\[\d+/\d+\] (\S+):(\S+):(\S+)", line.strip())
        if m:
            arts.append(m.groups())
    if not arts:
        raise SystemExit("no artifacts found in " + str(LOG))
    cp = []
    for g, a, v in arts:
        base = M2 / g.replace(".", "/") / a / v
        fname = f"{a}-{v}-aot.jar" if (g, a) == ("thheller", "shadow-cljs") else f"{a}-{v}.jar"
        p = base / fname
        if not p.exists():
            raise SystemExit(f"missing jar: {p}")
        cp.append(str(p))
    OUT.write_text(":".join(cp))
    print(f"wrote {OUT} ({len(cp)} jars)")


if __name__ == "__main__":
    main()
