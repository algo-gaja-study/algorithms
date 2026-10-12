import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        int beforeValue = -1;
        List<Integer> answerList = new ArrayList<>();

        for(int num : arr){
            if(beforeValue==num){
                continue;
            }
            answerList.add(num);
            beforeValue=num;
        }

        return answerList.stream()
                .mapToInt(i->i).toArray();
    }
}
