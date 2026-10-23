function solution(record) {
    const nickname = new Map();

    for (const line of record) {
        const [action, uid, name] = line.split(' ');
        if (action === 'Enter' || action === 'Change') {
            nickname.set(uid, name);
        }
    }

    const answer = [];

    for (const line of record) {
        const [action, uid] = line.split(' ');
        if (action === 'Enter') {
            answer.push(`${nickname.get(uid)}님이 들어왔습니다.`);
        } else if (action === 'Leave') {
            answer.push(`${nickname.get(uid)}님이 나갔습니다.`);
        }
    }

    return answer;
}
