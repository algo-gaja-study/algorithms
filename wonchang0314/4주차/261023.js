function solution(id_list, report, k) {
    const reportCount = new Map();
    const reportMap = new Map();

    id_list.forEach((id) => {
        reportCount.set(id, 0);
        reportMap.set(id, []);
    });

    for (const r of new Set(report)) {
        const [reporter, reported] = r.split(' ');
        reportCount.set(reported, reportCount.get(reported) + 1);
        reportMap.get(reporter).push(reported);
    }

    return id_list.map((id) =>
        reportMap.get(id).filter((target) => reportCount.get(target) >= k).length
    );
}
