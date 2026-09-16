import os
import subprocess
import sys
import tempfile
from datetime import UTC, datetime, timedelta
from pathlib import Path
from zoneinfo import ZoneInfo

SCRIPT = Path(__file__).with_name("compute_commit_timestamp.py")
SEOUL = ZoneInfo("Asia/Seoul")


def run_script(event_name: str, *, github_sha: str | None = None) -> datetime:
    env = os.environ.copy()
    env["GITHUB_EVENT_NAME"] = event_name

    if github_sha is not None:
        env["GITHUB_SHA"] = github_sha

    with tempfile.TemporaryDirectory() as directory:
        output_path = Path(directory) / "github-output"
        env["GITHUB_OUTPUT"] = str(output_path)

        subprocess.run(
            [sys.executable, str(SCRIPT)],
            check=True,
            env=env,
        )

        output = output_path.read_text(encoding="utf-8").splitlines()

    if len(output) != 1 or not output[0].startswith("value="):
        raise AssertionError(f"unexpected GitHub output: {output!r}")

    return datetime.fromisoformat(output[0].removeprefix("value="))


def previous_schedule_slot(value: datetime) -> datetime:
    value = value.astimezone(UTC)
    return value.replace(
        hour=value.hour - (value.hour % 3),
        minute=0,
        second=0,
        microsecond=0,
    )


def next_quarter(value: datetime) -> datetime:
    value = value.astimezone(SEOUL)
    return (
        value + timedelta(minutes=15 - (value.minute % 15))
    ).replace(
        second=0,
        microsecond=0,
    )


def verify_push() -> None:
    sha = subprocess.check_output(
        ["git", "rev-parse", "HEAD"],
        text=True,
    ).strip()
    expected = subprocess.check_output(
        ["git", "show", "-s", "--format=%cI", sha],
        text=True,
    ).strip()
    actual = run_script("push", github_sha=sha).isoformat()

    if actual != expected:
        raise AssertionError(f"push: expected {expected}, got {actual}")

    print(f"push: {actual}")


def verify_schedule() -> None:
    before = datetime.now(UTC)
    actual = run_script("schedule")
    after = datetime.now(UTC)
    expected = {
        previous_schedule_slot(before),
        previous_schedule_slot(after),
    }

    if actual.astimezone(UTC) not in expected:
        raise AssertionError(
            f"schedule: expected one of {sorted(expected)!r}, got {actual!r}"
        )

    print(f"schedule: {actual.isoformat()}")


def verify_manual() -> None:
    before = datetime.now(SEOUL)
    actual = run_script("workflow_dispatch")
    after = datetime.now(SEOUL)
    expected = {
        next_quarter(before),
        next_quarter(after),
    }

    if actual not in expected:
        raise AssertionError(
            f"workflow_dispatch: expected one of {sorted(expected)!r}, got {actual!r}"
        )

    print(f"workflow_dispatch: {actual.isoformat()}")


def main() -> None:
    verify_push()
    verify_schedule()
    verify_manual()
    print("all timestamp checks passed")


if __name__ == "__main__":
    main()
