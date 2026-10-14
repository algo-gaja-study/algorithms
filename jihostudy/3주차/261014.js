function solution(participant, completion) {
  // 한명만 찾으면됨
  // for (const people of participant) {
  //   let check = false;
  //   for (const [index, test] of completion.entries()) {
  //     if (people == test) {
  //       check = true;
  //       // 동명이인 대비: 찾은 인원을 완주목록에서 제거
  //       completion.splice(index, 1);
  //       break;
  //     }
  //   }
  //   if (check == false) {
  //     return people;
  //   }
  // }
  // NOTE: const doneSet = {} 는 Object.prototype을 상속해서,
  // 참가자 이름이 하필 constructor 면 doneSet["constructor"] 가 undefined가 아니라 상속된 함수를 반환합니다.

  // const doneSet = {};
  // for (const done_people of completion) {
  //   doneSet[done_people] = (doneSet[done_people] || 0) + 1;
  // }
  // // console.log(doneSet);
  // for (const find_people of participant) {
  //   // console.log("finding : ", find_people);
  //   if (doneSet[find_people] == undefined || doneSet[find_people] <= 0) {
  //     return find_people;
  //   }
  //   doneSet[find_people] -= 1;
  // }

  // NOTE: 안전하게 Map으로 처리
  const doneSet = new Map();
  for (const done_people of completion) {
    doneSet.set(done_people, (doneSet.get(done_people) ?? 0) + 1);
  }
  for (const find_people of participant) {
    const left = doneSet.get(find_people);
    if (!left) return find_people; // undefined 와 0 을 한 번에 처리
    doneSet.set(find_people, left - 1);
  }
}

console.log(solution(["leo", "kiki", "eden"], ["eden", "kiki"]));
console.log(
  solution(
    ["marina", "josipa", "nikola", "vinko", "filipa"],
    ["josipa", "filipa", "marina", "nikola"],
    /**
     * josipa : 1
     * filipa : 1
     * marina : 1
     * nikola : 1
     */
  ),
);
console.log(
  solution(["mislav", "stanko", "mislav", "ana"], ["stanko", "ana", "mislav"]),
);
