#!/usr/bin/env python3
"""매일 자정(KST) 전날 문제 풀이 여부를 확인하고, 미제출자의 회비를 README에 누적한다.

제출 판정: 전날 23:59:59(KST) 시점의 main 스냅샷에
  `{GitHub 아이디 또는 이름}/**/YYMMDD.*` 파일이 존재하면 제출로 본다.

환경 변수
  TARGET_DATE   검사할 날짜(YYYY-MM-DD). 비우면 KST 기준 어제.
  GITHUB_OUTPUT GitHub Actions 출력 파일 (워크플로우에서 자동 설정)
  ISSUE_BODY_PATH 이슈 본문을 쓸 경로
"""
import os
import re
import subprocess
import sys
from datetime import date, datetime, timedelta, timezone

KST = timezone(timedelta(hours=9))
FEE = 5000
START_DATE = date(2026, 10, 1)
README = "README.md"
WEEKDAYS = "월화수목금토일"
COMMIT_TAG = "회비 반영 ({})"


def git(*args: str) -> str:
    return subprocess.run(["git", *args], check=True, capture_output=True, text=True).stdout


def resolve_target_date() -> date:
    raw = os.environ.get("TARGET_DATE", "").strip()
    if raw:
        return date.fromisoformat(raw)
    return (datetime.now(KST) - timedelta(days=1)).date()


def section(text: str, heading: str) -> str:
    match = re.search(rf"^## {re.escape(heading)}.*?$(.*?)(?=^## |\Z)", text, re.M | re.S)
    if not match:
        sys.exit(f"README에서 '## {heading}' 섹션을 찾지 못했습니다.")
    return match.group(1)


def parse_participants(text: str) -> list[tuple[str, str]]:
    rows = re.findall(r"^\|\s*([^|\-\s][^|]*?)\s*\|\s*\[@([\w-]+)\]", section(text, "참여자"), re.M)
    if not rows:
        sys.exit("참여자 표에서 참여자를 찾지 못했습니다.")
    return rows


def has_submitted(paths: list[str], name: str, github_id: str, yymmdd: str) -> bool:
    owners = {name, github_id.lower()}
    for path in paths:
        parts = path.split("/")
        if len(parts) < 2:
            continue
        owner = parts[0] if parts[0] == name else parts[0].lower()
        if owner in owners and parts[-1].split(".")[0] == yymmdd:
            return True
    return False


def add_fee(text: str, name: str) -> tuple[str, int]:
    pattern = re.compile(rf"^(\|\s*{re.escape(name)}\s*\|\s*)([\d,]+)(원\s*\|)", re.M)
    match = pattern.search(text)
    if not match:
        sys.exit(f"'회식비 제공 천사' 표에 {name} 행이 없습니다. README에 추가해 주세요.")
    amount = int(match.group(2).replace(",", "")) + FEE
    text = text[: match.start()] + f"{match.group(1)}{amount:,}{match.group(3)}" + text[match.end():]
    return text, amount


def update_total(text: str) -> tuple[str, int]:
    table = section(text, "회식비 제공 천사")
    total = sum(int(v.replace(",", "")) for v in re.findall(r"^\|[^|]+\|\s*([\d,]+)원\s*\|", table, re.M))
    text, count = re.subn(r"\*\*총 회식비: [\d,]+원\*\*", f"**총 회식비: {total:,}원**", text)
    if count != 1:
        sys.exit("README에서 '**총 회식비: N원**' 줄을 찾지 못했습니다.")
    return text, total


def write_output(**values: str) -> None:
    out = os.environ.get("GITHUB_OUTPUT")
    lines = "".join(f"{k}={v}\n" for k, v in values.items())
    if out:
        with open(out, "a", encoding="utf-8") as f:
            f.write(lines)
    print(lines, end="")


def main() -> None:
    target = resolve_target_date()
    label = f"{target.isoformat()} ({WEEKDAYS[target.weekday()]})"

    if target < START_DATE or target.weekday() >= 5:
        print(f"{label}: 스터디 시작 전이거나 주말이라 건너뜁니다.")
        write_output(fined="false")
        return

    if git("log", "--oneline", "-F", f"--grep={COMMIT_TAG.format(target.isoformat())}").strip():
        print(f"{label}: 이미 회비가 반영된 날짜입니다.")
        write_output(fined="false")
        return

    deadline = f"{target.isoformat()} 23:59:59 +0900"
    snapshot = git("rev-list", "-1", f"--before={deadline}", "HEAD").strip()
    paths = git("ls-tree", "-r", "-z", "--name-only", snapshot).split("\0") if snapshot else []

    with open(README, encoding="utf-8") as f:
        text = f.read()

    yymmdd = target.strftime("%y%m%d")
    fined: list[tuple[str, str, int]] = []
    for name, github_id in parse_participants(text):
        if has_submitted(paths, name, github_id, yymmdd):
            print(f"  ✅ {name} (@{github_id})")
            continue
        text, amount = add_fee(text, name)
        fined.append((name, github_id, amount))
        print(f"  ❌ {name} (@{github_id}) → 누적 {amount:,}원")

    if not fined:
        print(f"{label}: 전원 제출 🎉")
        write_output(fined="false")
        return

    text, total = update_total(text)
    with open(README, "w", encoding="utf-8") as f:
        f.write(text)

    rows = "\n".join(f"| {n} | @{g} | +{FEE:,}원 | {a:,}원 |" for n, g, a in fined)
    body = (
        f"## {label} 미제출 회비\n\n"
        f"`{yymmdd}` 파일이 마감({target.isoformat()} 23:59 KST)까지 제출되지 않았습니다.\n\n"
        "| 이름 | GitHub | 추가 | 누적 |\n| --- | --- | --- | --- |\n"
        f"{rows}\n\n"
        f"**총 회식비: {total:,}원**\n"
    )
    body_path = os.environ.get("ISSUE_BODY_PATH", "fee_issue.md")
    with open(body_path, "w", encoding="utf-8") as f:
        f.write(body)

    write_output(
        fined="true",
        date=target.isoformat(),
        commit_message=f"chore: {COMMIT_TAG.format(target.isoformat())}",
        issue_title=f"[회비] {label} 미제출 {len(fined)}명",
    )


if __name__ == "__main__":
    main()
