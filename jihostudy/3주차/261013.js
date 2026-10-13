var search = function (nums, target) {
  // 2. Binary Search
  const length = nums.length;
  let [left, right] = [0, length - 1];

  // TODO: left == right인 경우 어떻게 대응? >> 생각할 필요 없음.
  while (left <= right) {
    const mid = Math.floor((left + right) / 2);

    const midValue = nums[mid];
    if (midValue == target) {
      return mid;
    } else if (midValue > target) {
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }
  return -1;
};
