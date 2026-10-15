class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int totalFloor = n%w==0 ? n/w : (n/w)+1;
        int numFloor= num%w==0 ? num/w : (num/w)+1;

        if(isSameFlow(numFloor,totalFloor)){
            int topBoxCnt = n%w==0 ? w:n%w;
            int numBoxCnt = num%w==0 ? w:num%w;
            if(topBoxCnt>=numBoxCnt){
                answer=totalFloor-numFloor;
            }else{
                answer=totalFloor-numFloor-1;
            }
        }else{
            int numExceptCnt =num%w==0?1:(w-num%w+1);
            int topCnt = n%w==0?w:n%w;
            if(topCnt<numExceptCnt){
                answer=totalFloor-numFloor-1;
            }else{
                answer=totalFloor-numFloor;
            }
        }


        return answer+1;
    }

    private boolean isSameFlow(int a, int b){
        if((a%2==0&&b%2==0)||(a%2!=0&&b%2!=0)){
            return true;
        }
        return false;
    }
}
