import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[][] parts = new String[files.length][3];

        for(int i=0;i<files.length;i++){
            parts[i] = selectPart(files[i]);
        }

        Arrays.sort(parts,(a,b)->{
            int headCompare = a[0].compareToIgnoreCase(b[0]);

            if(headCompare!=0){
                return headCompare;
            }

            return Integer.compare(
                    Integer.parseInt(a[1]),
                    Integer.parseInt(b[1])
            );
        });
        String[] answer = new String[parts.length];

        for(int i=0;i<parts.length;i++){
            StringBuilder sb = new StringBuilder();
            sb.append(parts[i][0]).append(parts[i][1]).append(parts[i][2]);

            answer[i]=sb.toString();
        }
        return answer;
    }

    private String[] selectPart(String fileName){
        String[] separate = new String[3];
        StringBuilder sb= new StringBuilder();

        int numberStart=0;

        while(!Character.isDigit(fileName.charAt(numberStart))){
            numberStart+=1;
        }
        int numberEnd=numberStart;

        while (numberEnd<fileName.length()
                && numberEnd-numberStart < 5
                && Character.isDigit(fileName.charAt(numberEnd))) {
            numberEnd++;
        }

        separate[0]=fileName.substring(0,numberStart);
        separate[1]=fileName.substring(numberStart,numberEnd);
        separate[2]=fileName.substring(numberEnd);

        return separate;
    }
}
