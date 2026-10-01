/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function (nums) {
  const set_numbers = new Set(nums);

  if (nums.length != set_numbers.size) {
    return true;
  }
  return false;
};
