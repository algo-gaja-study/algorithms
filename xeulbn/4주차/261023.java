import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();

        for(int i=0;i<records.length;i++){
            String[] info = records[i].split(" ");
            String[] hm = info[0].split(":");

            int time = 60*Integer.parseInt(hm[0])
                    +Integer.parseInt(hm[1]);
            String carNum = info[1];
            String io = info[2];

            if(io.equals("IN")){
                inTime.put(carNum,time);
            }else{
                int duration = time-inTime.get(carNum);
                totalTime.put(carNum,totalTime.getOrDefault(carNum,0)+duration);
                inTime.remove(carNum);
            }
        }

        int lastTime = 23*60+59;
        for(Map.Entry<String,Integer> entry : inTime.entrySet()){
            String car =entry.getKey();
            int duration = lastTime - inTime.get(car);

            totalTime.put(car,totalTime.getOrDefault(car,0)+duration);
        }

        List<String> carNums = new ArrayList<>(totalTime.keySet());
        int[] answer= new int[carNums.size()];

        Collections.sort(carNums);

        for(int i=0;i<carNums.size();i++){
            int remainTime=totalTime.get(carNums.get(i))-fees[0];

            answer[i] = fees[1]; // 기본 요금
            if (remainTime > 0) {
                int units = (int) Math.ceil((double) remainTime/fees[2]);
                answer[i] += units*fees[3];
            }
        }

        return answer;
    }
}
