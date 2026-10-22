import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer>totalTime = new HashMap<>();
        Map<String, Integer> inTime = new HashMap<>();

        for(int i=0;i<records.length;i++){
            String[] info=records[i].split(" ");
            String[] timeArr = info[0].split(":");
            int time = 60*Integer.parseInt(timeArr[0])
                    +Integer.parseInt(timeArr[1]);

            String carNum=info[1];
            String io = info[2];

            if(io.equals("IN")){
                inTime.put(carNum,time);
            }else{
                int duration= time-inTime.get(carNum);
                totalTime.put(carNum,totalTime.getOrDefault(carNum,0)+duration);
                inTime.remove(carNum);
            }
        }

        for(Map.Entry<String,Integer> entry : inTime.entrySet()){
            String car = entry.getKey();
            int duration = 60*23+59-entry.getValue();

            totalTime.put(car,totalTime.getOrDefault(car,0)+duration);
        }

        List<String> carNums = new ArrayList<>(totalTime.keySet());

        int[] answer= new int[carNums.size()];
        //차량번호 기준 작은거부터 청구할 주차 요금 반환
        Collections.sort(carNums);

        //기본시간 : fees[0] , 기본요금 : fees[1], 단위 시간 : fees[2], 단위 요금 : fees[3]
        for(int i=0;i<carNums.size();i++){
            int remainTime=totalTime.get(carNums.get(i))-fees[0];

            answer[i] = fees[1]; // 기본 요금
            if (remainTime > 0) {
                int units = (int) Math.ceil((double) remainTime / fees[2]);
                answer[i] += units*fees[3];
            }
        }


        return answer;
    }
}
