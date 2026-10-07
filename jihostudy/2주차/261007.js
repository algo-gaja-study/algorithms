var twoSum = function (nums, target) {
  const seen = {}; // 탐색한 숫자

  for (const [index, num] of nums.entries()) {
    // console.log(seen);

    const complement = target - num;

    if (seen[complement] != undefined) {
      return [seen[complement], index];
    }
    seen[num] = index;
  }
};
