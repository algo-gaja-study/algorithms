import java.util.*;

class Solution {

    private static final int[] dx={-1,1,0,0};
    private static final int[] dy = {0,0,-1,1};
    private static boolean[][] visited;

    public int[] solution(String[] maps) {
        String[][] map = changeTo(maps);
        List<Integer> answer = new ArrayList<>();
        visited = new boolean[map.length][map[0].length];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(!map[i][j].equals("X")&& !visited[i][j]){
                    int continuousRegion=bfs(i,j,map);
                    answer.add(continuousRegion);
                }
            }
        }

        if(answer.size()==0 || answer.isEmpty()){
            return new int[]{-1};
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }

    private int bfs(int x, int y, String[][] map){
        Queue<int[]> q = new ArrayDeque<>();
        int returnValue=0;

        q.offer(new int[]{x,y});
        returnValue+=Integer.parseInt(map[x][y]);
        visited[x][y]=true;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i=0;i<4;i++){
                int nextX= currX+dx[i];
                int nextY = currY+dy[i];

                if(nextX<0||nextX>=map.length||nextY<0||nextY>=map[0].length){
                    continue;
                }

                if(map[nextX][nextY].equals("X")||visited[nextX][nextY]){
                    continue;
                }

                q.offer(new int[]{nextX,nextY});
                returnValue+=Integer.parseInt(map[nextX][nextY]);
                visited[nextX][nextY]=true;
            }
        }
        return returnValue;
    }

    private String[][] changeTo(String[] maps){
        String[][] returnMap = new String[maps.length][maps[0].length()];

        for(int i=0;i<maps.length;i++){
            returnMap[i]=maps[i].split("");
        }

        return returnMap;
    }
}
