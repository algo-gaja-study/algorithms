import java.util.*;

class Solution {

    private static String inWord = "님이 들어왔습니다.";
    private static String outWord = "님이 나갔습니다.";

    public String[] solution(String[] record) {
        Map<String, String> idName = new HashMap<>();

        for(int i=0;i<record.length;i++){
            String[] log = record[i].split(" ");
            String action = log[0];
            String id = log[1];

            if(action.equals("Enter")){
                String name = log[2];
                idName.put(id,name);

            }else if(action.equals("Change")){
                String name = log[2];
                idName.put(id,name);
            }
        }
        List<String> result = new ArrayList<>();

        for(int i=0;i<record.length;i++){
            StringBuilder sb = new StringBuilder();
            String[] log = record[i].split(" ");
            String action = log[0];
            String id = log[1];

            if(action.equals("Enter")){
                sb.append(idName.get(id)).append(inWord);
            }else if(action.equals("Leave")){
                sb.append(idName.get(id)).append(outWord);
            }else{
                continue;
            }
            result.add(sb.toString());
        }

        return result.stream().toArray(String[]::new);
    }
}
