import java.util.*;

class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        answer[0]=findGCD(n,m);
        answer[1]=findLCM(n,m,answer[0]);

        return answer;
    }

    private int findGCD(int n, int m){
        int bigNum = Math.max(n,m);
        int smallNum= Math.min(n,m);
        int tmp=0;

        while(smallNum!=0){
            tmp = bigNum%smallNum;
            bigNum=smallNum;
            smallNum=tmp;
        }

        return bigNum;
    }

    private int findLCM(int n,int m,int gcd){
        return (n*m)/gcd;
    }
}
