import java.util.*;

class Solution {
    public int solution(int[][] signals) {
        int answer = 0;
        int maxTime = 1;

        int[] cycle = new int[signals.length];

        for(int i=0;i<signals.length;i++){
            cycle[i] = signals[i][0]+signals[i][1]+signals[i][2];
            maxTime = lcm(maxTime,cycle[i]);
        }

        for(int i=1;i<=maxTime;i++){
            boolean isAllYellow = true;

            for (int j = 0; j < signals.length; j++) {
                int remain = (i - 1) % cycle[j];
                int greenTime = signals[j][0];
                int yellowTime = signals[j][1];

                if (remain<greenTime || remain>=greenTime + yellowTime) {
                    isAllYellow = false;
                    break;
                }
            }

            if(isAllYellow){
                return i;
            }

        }
        return -1;
    }

    private int lcm(int a, int b){
        return a/gcd(a,b)*b;
    }

    private int gcd(int a, int b){

        while(b>0){
            int tmp = a%b;
            a=b;
            b=tmp;
        }

        return a;
    }
}
