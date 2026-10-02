import java. util. *;

class Solution {
    public int solution(String s) {
        int curMinValue = s.length();

        for(int size =1; size<=s.length()/2;size++){
            int compressedSize = calculateSize(s,size);
            curMinValue = Math.min(curMinValue,compressedSize);
        }

        return curMinValue;
    }

    private int calculateSize(String s, int size) {
        StringBuilder result = new StringBuilder();

        String prev = s.substring(0, size);
        int count = 1;

        for (int start=size; start < s.length(); start+=size) {
            int end = Math.min(start + size, s.length());
            String current = s.substring(start, end);

            if (prev.equals(current)) {
                // 1. 같은 조각이므로 반복 횟수 증가
                count+=1;
            } else {
                // 2. 지금까지 센 묶음을 result에 추가
                //    count가 2 이상일 때만 숫자를 붙이고,
                //    prev는 항상 붙인다

                if(count>=2){
                    result.append(count);
                }
                result.append(prev);
                // 3. current를 새로운 prev로 설정하고 count 초기화
                prev=current;
                count =1;
            }
        }

        // 4. 마지막으로 세던 묶음도 result에 추가
        if (count >= 2) {
            result.append(count);
        }
        result.append(prev);

        return result.length();
    }
}
