// https://school.programmers.co.kr/learn/courses/30/lessons/42576

function solution(participant, completion) {
    var answer = '';
    
    const m = new Map();
    
    participant.forEach(p => {
        if (m.set(p, (m.get(p) || 0) + 1)); 
    })
    
    completion.forEach(c => {
        const count = m.get(c);
        if (count) {
            if (count > 1) m.set(c, m.get(c) - 1);
            else m.delete(c);
        }
    })
    return Array.from(m)[0][0];
}
