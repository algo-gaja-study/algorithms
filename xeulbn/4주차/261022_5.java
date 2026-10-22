import java.util.*;

class Solution {

    private static boolean[][] visited;
    private static final int[] dx={-1,1,0,0};
    private static final int[] dy ={0,0,-1,1};

    public int solution(String[] maps) {
        String[][] map = changeMap(maps);
        int answer=-1;

        int[] startIndex= new int[2];
        int[] leverIndex = new int[2];
        int[] exitIndex = new int[2];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j].equals("S")){
                    startIndex[0]=i;
                    startIndex[1]=j;
                }

                if(map[i][j].equals("L")){
                    leverIndex[0]=i;
                    leverIndex[1]=j;
                }

                if(map[i][j].equals("E")){
                    exitIndex[0]=i;
                    exitIndex[1]=j;
                }
            }
        }

        int toLever=bfs(startIndex,leverIndex,map);
        int toExit=bfs(leverIndex,exitIndex,map);

        if(toLever!=-1 && toExit!=-1){
            answer=toLever+toExit;
        }

        return answer;
    }

    private int bfs(int[] start,int[] end,String[][] map){
        Queue<int[]> q =new ArrayDeque<>();
        visited=new boolean[map.length][map[0].length];
        q.offer(new int[]{start[0],start[1],0});

        visited[start[0]][start[1]]=true;

        while(!q.isEmpty()){
            int[] curr= q.poll();
            int currX=curr[0];
            int currY=curr[1];
            int currDistance = curr[2];

            for(int i=0;i<4;i++){
                int nextX = currX+dx[i];
                int nextY = currY+dy[i];
                int nextDistance= currDistance+1;

                if(nextX<0||nextY<0||nextX>=map.length||nextY>=map[0].length){
                    continue;
                }

                if(visited[nextX][nextY] || map[nextX][nextY].equals("X")){
                    continue;
                }

                if(nextX==end[0]&&nextY==end[1]){
                    return nextDistance;
                }

                q.offer(new int[]{nextX,nextY,nextDistance});
                visited[nextX][nextY]=true;
            }
        }

        return -1;
    }

    private String[][] changeMap(String[] maps){
        String[][] returnMap = new String[maps.length][maps[0].length()];

        for(int i=0;i<maps.length;i++){
            returnMap[i]=maps[i].split("");
        }
        return returnMap;
    }
}
