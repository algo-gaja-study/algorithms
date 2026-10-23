import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (a,b)->{
            int compare = Character.compare(a.charAt(n),b.charAt(n));

            if(compare!=0){
                return compare;
            }
            return a.compareTo(b);
        });


        return strings;
    }
}
