/** 문제
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 */

/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function (s, t) {
  // 두단어의 구성이 같으면 true, 다르면 false

  const set_s = {};
  for (const char of s) {
    if (set_s[char] == undefined) {
      set_s[char] = 1;
    } else {
      set_s[char] += 1;
    }
  }

  let answer = true;
  // 실패1. 길이가 다른 경우
  if (s.length != t.length) answer = false;

  for (const char of t) {
    // 실패2. 찾으려는 개수를 다했거나 없는 경우
    if (set_s[char] <= 0 || set_s[char] == undefined) {
      answer = false;
      break;
    }
    // 있는 경우
    else {
      set_s[char] -= 1;
    }
  }
  return answer;
};

console.log(isAnagram("anagram", "nagaram"));
console.log(isAnagram("rat", "car"));
console.log(isAnagram("a", "ab"));
