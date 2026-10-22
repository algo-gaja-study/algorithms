import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left =0;
        int sum=0;

        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];

        for(int right=0;right<sequence.length;right++){
            sum+=sequence[right];

            while(sum>k){
                sum-=sequence[left];
                left+=1;
            }

            if(sum==k){
                int length = right-left+1;

                if (length < minLength) {
                    minLength = length;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
        }


        return answer;
    }
}
