import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String,Integer> inTime = new HashMap<>();
        Map<String,Integer> totalTime = new HashMap<>();

        for(int i=0;i<records.length;i++){
            String[] info=records[i].split(" ");
            String[] hm = info[0].split(":");
            int time = Integer.parseInt(hm[0]) * 60
                    + Integer.parseInt(hm[1]);

            String carNum = info[1];
            String io = info[2];

            if(io.equals("IN")){
                inTime.put(carNum,time);
            }else{
                int duration = time - inTime.get(carNum);
                totalTime.put(carNum,
                        totalTime.getOrDefault(carNum,0)+duration);

                inTime.remove(carNum);
            }
        }

        int endTime = 23 * 60 + 59;
        for (Map.Entry<String, Integer> entry : inTime.entrySet()) {
            String carNum = entry.getKey();
            int startTime = entry.getValue();

            int duration = endTime - startTime;

            totalTime.put(
                    carNum,
                    totalTime.getOrDefault(carNum, 0) + duration
            );
        }

        List<String> carNums = new ArrayList<>(totalTime.keySet());
        Collections.sort(carNums);

        int[] answer = new int[carNums.size()];

        for (int i = 0; i < carNums.size(); i++) {
            String carNum = carNums.get(i);
            int minutes = totalTime.get(carNum);

            int fee = fees[1];

            if (minutes > fees[0]) {
                int extra = minutes - fees[0];
                int units = (extra + fees[2] - 1) / fees[2];

                fee += units * fees[3];
            }

            answer[i] = fee;
        }

        return answer;
    }
}
