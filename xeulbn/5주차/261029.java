import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> missingScore = new HashMap<>();

        for(int i=0;i<name.length;i++){
            missingScore.put(name[i],yearning[i]);
        }

        for(int i=0;i<photo.length;i++){
            String[] names = photo[i];
            int score=0;
            for(String str : names){
                score+=missingScore.getOrDefault(str,0);
            }
            answer[i]=score;
        }

        return answer;
    }
}
