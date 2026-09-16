import os
import subprocess
from datetime import UTC, datetime, timedelta
from zoneinfo import ZoneInfo

SEOUL = ZoneInfo("Asia/Seoul")


def get_push_timestamp() -> str:
    return subprocess.check_output(
        [
            "git",
            "show",
            "-s",
            "--format=%cI",
            os.environ["GITHUB_SHA"],
        ],
        text=True,
    ).strip()


def get_schedule_timestamp() -> str:
    now = datetime.now(UTC)

    return (
        now.replace(
            hour=now.hour - (now.hour % 3),
            minute=0,
            second=0,
            microsecond=0,
        )
        .astimezone(SEOUL)
        .isoformat()
    )


def get_manual_timestamp() -> str:
    now = datetime.now(SEOUL)

    return (
        (now + timedelta(minutes=15 - (now.minute % 15)))
        .replace(
            second=0,
            microsecond=0,
        )
        .isoformat()
    )


def compute_commit_timestamp() -> str:
    event_name = os.environ["GITHUB_EVENT_NAME"]

    if event_name == "push":
        return get_push_timestamp()

    if event_name == "schedule":
        return get_schedule_timestamp()

    return get_manual_timestamp()


def write_github_output(value: str) -> None:
    with open(
        os.environ["GITHUB_OUTPUT"],
        "a",
        encoding="utf-8",
    ) as output:
        output.write(f"value={value}\n")


def main() -> None:
    write_github_output(compute_commit_timestamp())


if __name__ == "__main__":
    main()
