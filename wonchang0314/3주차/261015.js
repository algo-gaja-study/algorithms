// https://school.programmers.co.kr/learn/courses/30/lessons/42577

// 풀이 1: 정렬 - 접두어 관계인 번호는 사전순 정렬 시 인접한다
function solution(phone_book) {
    var answer = true;
    const sorted = phone_book.sort();
    
    for(let i = 0; i < sorted.length - 1; i++) {
        if (sorted[i + 1].startsWith(sorted[i])) return false
    }
    return answer;
}

// 풀이 2: 해시 - 각 번호의 접두어가 Set에 있는지 확인한다
function solutionHash(phone_book) {
    const set = new Set(phone_book);

    for (const phone of phone_book) {
        for (let len = 1; len < phone.length; len++) {
            if (set.has(phone.slice(0, len))) return false;
        }
    }
    return true;
}
