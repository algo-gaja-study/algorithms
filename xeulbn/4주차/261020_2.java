import java.util.*;

class Solution {

    private boolean[][] visited;
    private List<Integer> sums = new ArrayList<>();
    private int[] dx = {-1,1,0,0};
    private int[] dy= {0,0,-1,1};

    public int[] solution(String[] maps) {
        String[][] map = new String[maps.length][maps[0].length()];
        visited= new boolean[maps.length][maps[0].length()];

        for(int i=0;i<maps.length;i++){
            map[i]=maps[i].split("");
        }

        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[0].length();j++){
                if(!visited[i][j]&&!map[i][j].equals("X")){
                    bfs(i,j,map);
                }
            }
        }

        if(sums.isEmpty()){
            return new int[]{-1};
        }

        return sums.stream()
                .mapToInt(Integer::intValue).sorted().toArray();

    }

    private void bfs(int x, int y, String[][] map){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        visited[x][y]=true;
        int sum = Integer.parseInt(map[x][y]);

        while(!q.isEmpty()){
            int[] node =q.poll();
            int currX=node[0];
            int currY=node[1];

            for(int i=0;i<4;i++){
                int nextX = currX+dx[i];
                int nextY = currY+dy[i];

                if(nextX<0||nextY<0||nextX>=map.length||nextY>=map[0].length){
                    continue;
                }

                if(visited[nextX][nextY]){
                    continue;
                }

                if(!map[nextX][nextY].equals("X")&&!visited[nextX][nextY]){
                    visited[nextX][nextY]=true;
                    q.offer(new int[]{nextX,nextY});

                    sum+=Integer.parseInt(map[nextX][nextY]);
                }

            }

        }


        sums.add(sum);
    }
}
