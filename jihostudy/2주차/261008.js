/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function(s) {
    const stack = [];
    const pairs = { ")": "(", "]": "[", "}": "{" };

    for (const c of s) {
        if (c in pairs) {
            // 닫는 괄호: 스택 top이 짝이어야 함
            if (stack.pop() !== pairs[c]) return false;
        } else {
            // 여는 괄호: push
            stack.push(c);
        }
    }

    // 다 처리하고 스택이 비어있어야 유효
    return stack.length === 0;
};
