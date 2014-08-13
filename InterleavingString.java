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
