import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0;i<priorities.length;i++){
            q.offer(new int[]{i,priorities[i]});
        }

        int answer= 0;

        while(!q.isEmpty()){
            int[] curr=q.poll();
            boolean hasHigherPriority = false;

            for(int[] process : q){
                if (process[1] > curr[1]) {
                    hasHigherPriority = true;
                    break;
                }
            }

            if (hasHigherPriority) {
                q.offer(curr);
            } else {
                answer++;

                if (curr[0] == location) {
                    return answer;
                }
            }
        }

        return answer;
    }
}
