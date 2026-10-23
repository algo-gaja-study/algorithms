import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0;i<priorities.length;i++){
            q.offer(new int[]{i,priorities[i]});
        }
        int answer=0;

        while(!q.isEmpty()){
            int[] curr=q.poll();
            int currIndex = curr[0];
            int currPriorities = curr[1];
            boolean hasHigherPriorities = false;

            for(int[] process : q){
                if(process[1]>currPriorities){
                    hasHigherPriorities=true;
                    break;
                }
            }

            if(hasHigherPriorities){
                q.offer(curr);
            }else{
                answer+=1;

                if(curr[0]==location){
                    return answer;
                }
            }

        }


        return answer;
    }
}
