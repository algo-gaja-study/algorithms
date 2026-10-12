// https://school.programmers.co.kr/learn/courses/30/lessons/86491
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var answer: Int = 0
        var maxH: Int = 0
        var maxW: Int = 0
        
        for((w, h) in sizes) {
            maxH = max(maxH, max(w, h))
            maxW = max(maxW, min(w, h))
        }
        return maxH * maxW
    }
}
