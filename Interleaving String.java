// The Dp method is Similar with "Decode Ways" problem.
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || (s1.length() + s2.length() != s3.length())) {
            return false;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        boolean result = isInterleaveCore(s1, s2, s3, 0, 0, 0, dp);
        return result;
    }
    
    public boolean isInterleaveCore(String s1, String s2, String s3, int sOne, int sTwo, int sThree, int[][][] dp) {
        if (sOne == s1.length() && sTwo == s2.length() && sThree == s3.length()) {
            return true;
        }
        if (dp[sOne][sTwo][sThree] != 0) {
            if (dp[sOne][sTwo][sThree] == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        boolean subResult = false;
        if (sOne < s1.length() && s1.charAt(sOne) == s3.charAt(sThree)) {
            subResult |= isInterleaveCore(s1, s2, s3, sOne + 1, sTwo, sThree + 1, dp);
            if (subResult == true) {
                dp[sOne][sTwo][sThree] = 1;
                return true;
            }
        }
        if (sTwo < s2.length() && s2.charAt(sTwo) == s3.charAt(sThree)) {
            subResult |= isInterleaveCore(s1, s2, s3, sOne, sTwo + 1, sThree + 1, dp);
            if (subResult == true) {
                dp[sOne][sTwo][sThree] = 1;
                return true;
            }
        }
        dp[sOne][sTwo][sThree] = -1;
        return false;
    }
}



// For Loop DP
public boolean isInterleave(String s1, String s2, String s3) {
    if (s1 == null || s2 == null || s3 == null || (s1.length() + s2.length()) != s3.length()) {
        return false;
    }
    
    boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
    dp[0][0] = true;
    for (int i = 1; i < s1.length() + 1; i++) {
        if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
            break;
        }
        dp[i][0] = true;
    }
    
    for (int i = 1; i < s2.length() + 1; i++) {
        if (s2.charAt(i - 1) != s3.charAt(i - 1)) {
            break;
        }
        dp[0][i] = true;
    }
    
    for (int i = 1; i < s1.length() + 1; i++) {
        for (int j = 1; j < s2.length() + 1; j++) {
            if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
                dp[i][j] = true;
            }
            else if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
                dp[i][j] = true;
            }
        }
    }
    
    return dp[s1.length()][s2.length()];
}
