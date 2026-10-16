import java.util.*;

class Solution {

    public int solution(int[] numbers) {
        int totalAnswer = 45;

        for(int i=0;i<numbers.length;i++){
            totalAnswer=totalAnswer-numbers[i];
        }

        return totalAnswer;
    }

}
