import java.util.*;

class Solution {
    private static final char blur = '*';

    public int solution(String message, int[][] spoiler_ranges) {

        //스포 방지 처리
        String blurString = blurMessage(message,spoiler_ranges);

        //단어 배열로 분리
        String[] originWords = message.split(" ");
        String[] blurWords = blurString.split(" ");

        //중요한 단어 구하기
        int answer = calculate(originWords,blurWords);

        return answer;
    }

    private int calculate(String[] originWords, String[] blurWords){
        //일반 단어
        Set<String> words = new HashSet<>();
        //스포 방지 단어
        Set<String> spoilerWords = new HashSet<>();

        int len = blurWords.length;

        for(int i=0;i<len;i++){
            //스포 방지 단어가 아닌 경우
            if(blurWords[i].indexOf(blur)==-1){
                words.add(originWords[i]);
            }else{
                spoilerWords.add(originWords[i]);
            }
        }

        spoilerWords.removeAll(words);

        return spoilerWords.size();
    }


    private String blurMessage(String message, int[][] spoiler_ranges){
        StringBuilder copy = new StringBuilder(message);

        for(int[] range: spoiler_ranges){
            int start = range[0];
            int end = range[1];

            for(int i=start;i<=end;i++){
                if(message.charAt(i)!=' '){
                    copy.setCharAt(i,blur);
                }
            }
        }

        return copy.toString();
    }
}
