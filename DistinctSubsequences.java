// Do not forget delete all unrelated characters which are not appeared in T string.
public class Solution {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) {
            return 0;
        }
        Map<Character, Integer> hasMap = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            char curChar = T.charAt(i);
            if (!hasMap.containsKey(curChar)) {
                hasMap.put(curChar, 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (hasMap.containsKey(S.charAt(i))) {
                sb.append(S.charAt(i));
            }
        }
        String tempS = sb.toString();
        int[][] dp = new int[tempS.length() + 1][T.length() + 1];
        for (int i = 0; i <= tempS.length(); i++) {
            for (int j = 0; j <= T.length(); j++) {
                dp[i][j] = -1;
            }
        }
        int result = numDistinctCore(tempS, T, 0, 0, dp);
        return result;
    }
    
    public int numDistinctCore(String S, String T, int sIndex, int tIndex, int[][] dp) {
        if (tIndex == T.length()) {
            return 1;
        }
        if (dp[sIndex][tIndex] != -1) {
            return dp[sIndex][tIndex];
        }
        int subResult = 0;
        if (sIndex < S.length()) {
            subResult += numDistinctCore(S, T, sIndex + 1, tIndex, dp);
        }
        if (sIndex < S.length() && S.charAt(sIndex) == T.charAt(tIndex)) {
            subResult += numDistinctCore(S, T, sIndex + 1, tIndex + 1, dp);
        }
        dp[sIndex][tIndex] = subResult;
        return subResult;
    }
}
