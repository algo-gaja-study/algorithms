function solution(record) {
    var answer = [];
    const nameMap = new Map();
    
    record.forEach(r => {
        const [action, id, nickName] = r.split(' ');
        
        if (action === 'Enter' || action === 'Change') {
            nameMap.set(id, nickName);
        }
    })
    
    record.forEach(r => {
        const [action, id] = r.split(' ');
        
        if (action === 'Enter') {
            answer.push(`${nameMap.get(id)}님이 들어왔습니다.`)
        }
        else if (action === 'Leave') {
            answer.push(`${nameMap.get(id)}님이 나갔습니다.`)
        }
    })
    
    return answer;
}
