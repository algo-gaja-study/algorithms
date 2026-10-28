// https://school.programmers.co.kr/learn/courses/30/lessons/12973

function solution(s){
    let answer = 0;
    let stack = [];
    
    for(let char of s) {
        if(stack.length && stack[stack.length - 1] === char) stack.pop();
        else stack.push(char);
    }
    if(stack.length === 0) answer = 1;
    return answer;
}
