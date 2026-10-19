import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        int[] xCount = new int[10];
        int[] yCount = new int[10];

        for(int i=0;i<X.length();i++){
            xCount[X.charAt(i)-'0']+=1;
        }
        for(int i=0;i<Y.length();i++){
            yCount[Y.charAt(i)-'0']+=1;
        }

        for(int i=9;i>=0;i--){
            if(xCount[i]==0||yCount[i]==0){
                continue;
            }
            for(int j=0;j<Math.min(xCount[i],yCount[i]);j++){
                answer.append(i);
            }
        }

        if(answer==null||answer.length()==0){
            return "-1";
        }
        if(answer.toString().startsWith("0")){
            return "0";
        }

        return answer.toString();
    }

}
