import java.util.*;

class Solution {

    private int answer;

    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        pick(n, 1, 0, new int[5], q, ans);
        return answer;
    }

    private void pick(int n, int start, int depth,
                      int[] candidate, int[][] q, int[] ans) {

        if (depth == 5) {
            Set<Integer> selected = new HashSet<>();
            for (int num : candidate) {
                selected.add(num);
            }

            for (int i=0; i<q.length; i++) {
                int count = 0;

                for (int num : q[i]) {
                    if (selected.contains(num)) {
                        count++;
                    }
                }
                if (count != ans[i]) {
                    return;
                }
            }
            answer++;
            return;
        }

        for (int num=start; num<=n; num++) {
            candidate[depth] = num;

            pick(n, num + 1, depth + 1, candidate, q, ans);
        }
    }


}
