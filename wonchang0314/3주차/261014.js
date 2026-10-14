// https://leetcode.com/problems/reverse-integer/
/**
 * @param {number} x
 * @return {number}
 */
var reverse = function(x) {
    const X = x > 0 ? x : x * -1
    const reversed = Number(String(X).split('').reverse().join(''))
    const result = x > 0 ? reversed : reversed * -1;
    return result < -(2 ** 31) || result > 2 ** 31 - 1 ? 0 : result;
};
