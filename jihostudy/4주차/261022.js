function solution(id_list, report, k) {
    // 1. 같은 사람이 같은 유저를 여러 번 신고해도 1회로만 처리
    const uniqueReports = new Set(report);

    // 2. "신고당한 유저 -> 신고한 유저들의 집합" 방향으로 모은다
    const reportedBy = new Map();
    for (const id of id_list) {
        reportedBy.set(id, new Set());
    }

    for (const r of uniqueReports) {
        const [reporter, reported] = r.split(' ');
        reportedBy.get(reported).add(reporter);
    }

    // 3. 각 유저가 받을 메일 수
    const mailCount = new Map(id_list.map(id => [id, 0]));

    for (const [, reporters] of reportedBy) {
        // 정지 기준 미달이면 아무 일도 일어나지 않음
        if (reporters.size < k) continue;

        // 정지된 유저를 신고했던 사람들에게 메일 1통씩
        for (const reporter of reporters) {
            mailCount.set(reporter, mailCount.get(reporter) + 1);
        }
    }

    // 4. 결과는 id_list 순서대로
    return id_list.map(id => mailCount.get(id));
}
