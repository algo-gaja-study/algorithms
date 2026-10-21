import java.util.*;

class Solution {

    private boolean[][] connected;

    public int solution(int n, int[][] wires) {
        connected = new boolean[n+1][n+1];


        for(int[] wire : wires){
            int node1=wire[0];
            int node2=wire[1];

            connected[node1][node2]=true;
            connected[node2][node1]=true;
        }

        int answer =n;


        for(int[] wire : wires){
            int node1= wire[0];
            int node2 = wire[1];

            connected[node1][node2]=false;
            connected[node2][node1]=false;

            int count = bfs(node1, n, connected);

            int difference = Math.abs(count-(n-count));
            answer = Math.min(answer, difference);

            connected[node1][node2]=true;
            connected[node2][node1]=true;
        }

        return answer;
    }

    private int bfs(int node, int total, boolean[][] connected){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[total+1];
        q.offer(node);
        visited[node]=true;
        int linked=1;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next=1; next<=total; next++) {
                if (connected[now][next] && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    linked+=1;
                }
            }
        }

        return linked;
    }
}
