import java.util.*;

class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n>=a){
            int exchangeCount = n/a;
            int remain = n%a;
            int received = exchangeCount*b;

            answer+=received;
            n=received+remain;
        }


        return answer;
    }
}
