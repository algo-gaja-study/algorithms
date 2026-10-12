/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    let minPrice = Infinity;
    let maxProfit = 0;

    for (const price of prices) {
        if (price < minPrice) {
            minPrice = price;               // 더 싼 매수 시점 발견
        } else {
            maxProfit = Math.max(maxProfit, price - minPrice); // 오늘 팔았을 때 이익
        }
    }

    return maxProfit;
};
