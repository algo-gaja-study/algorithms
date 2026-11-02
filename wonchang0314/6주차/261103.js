// https://school.programmers.co.kr/learn/courses/30/lessons/12931

function solution(n)
{
    let answer = String(n).split('').reduce((a,b)=>{
        return Number(a) + Number(b);
        
    },0)

    return answer;
}
