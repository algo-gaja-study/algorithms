import java.util.*;

class Solution {

    private static Map<String,String> idName;
    private static final String inWord = "님이 들어왔습니다.";
    private static final String outWord = "님이 나갔습니다.";

    public String[] solution(String[] record) {
        idName= new HashMap<>();

        for(int i=0;i<record.length;i++){
            String[] info = record[i].split(" ");
            String action = info[0];
            String id = info[1];

            if(action.equals("Enter")){
                String name = info[2];
                idName.put(id,name);
            }else if(action.equals("Change")){
                String name = info[2];
                idName.put(id,name);
            }
        }

        String[] answer=changeToKor(record);

        return answer;
    }

    private String[] changeToKor(String[] record){
        List<String> korResult = new ArrayList<>();

        for(int i=0;i<record.length;i++){
            String[] info = record[i].split(" ");
            String action = info[0];
            String id = info[1];
            String name = idName.get(id);

            StringBuilder sb = new StringBuilder();

            if(action.equals("Enter")){
                sb.append(name).append(inWord);
                korResult.add(sb.toString());
            }else if(action.equals("Leave")){
                sb.append(name).append(outWord);
                korResult.add(sb.toString());
            }else{
                continue;
            }
        }

        return korResult.stream().toArray(String[]::new);
    }
}
