/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function (s) {
  // TODO: 특수문자 제거했던 Replace 구문 복습 필요
  // non-alphanumeric characters 제거
  const string = s.toLowerCase().replace(/[^a-z0-9]/g, "");

  const length = string.length;
  let [left, right] = [0, length - 1];
  while (left <= right) {
    if (string[left] != string[right]) return false;

    left += 1;
    right -= 1;
  }
  return true;
};

console.log(isPalindrome("A man, a plan, a canal: Panama"));

console.log(isPalindrome("race a car"));
console.log(isPalindrome(" "));
