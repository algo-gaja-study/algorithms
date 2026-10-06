import java.util.*;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int totalSum = 0;
        int leftIdx =0;
        int minLength = Integer.MAX_VALUE;

        for (int rightIdx = 0; rightIdx < nums.length; rightIdx++) {
            // 오른쪽 숫자 하나를 현재 구간에 포함
            totalSum += nums[rightIdx];

            // 조건을 만족하는 동안 왼쪽을 줄여보기
            while (totalSum >= target) {
                minLength = Math.min(minLength, rightIdx - leftIdx + 1);

                totalSum -= nums[leftIdx];
                leftIdx++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
