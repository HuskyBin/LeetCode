// Memory Cache Method
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = -1;
        }
        int[] result = new int[1];
        numDecodingsCore(s, 0, dp);
        return dp[0];
    }
    
    public int numDecodingsCore(String s, int index, int[] dp) {
        if (index == s.length()) {
            return 1;
        }
        if (dp[index] >= 0) {
            return dp[index];
        }
        int subOneResult = 0;
        if (s.charAt(index) >= '1' && s.charAt(index) <= '9') {
            subOneResult = numDecodingsCore(s, index + 1, dp);
        }
        if (isValidForTwoIndex(s, index)) {
            subOneResult += numDecodingsCore(s, index + 2, dp);
        }
        dp[index] = subOneResult;
        return subOneResult;
    }
    
    public boolean isValidForTwoIndex(String s, int index) {
        if (index == s.length() - 1) {
            return false;
        }
        if (s.charAt(index) == '1') {
            return true;
        }
        if (s.charAt(index) == '2' && s.charAt(index + 1) >= '0' && s.charAt(index + 1) <= '6') {
            return true;
        }
        return false;
    }
}


// Update 07/09/2014
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] dp = new int[s.length()];
        int result = numDecodingCore(s, 0, dp);
        return result;
    }
    
    private int numDecodingCore(String s, int index, int[] dp) {
        if (index >= s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (dp[index] > 0) {
            return dp[index];
        }
        int result = numDecodingCore(s, index + 1, dp);
        if (isVlaidForTwoDigit(s, index)) {
            result += numDecodingCore(s, index + 2, dp);
        }
        dp[index] = result;
        return result;
    }
    
    private boolean isVlaidForTwoDigit(String s, int index) {
        char curChar = s.charAt(index);
        if (index == s.length() - 1) {
            return false;
        }
        char nextChar = s.charAt(index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(curChar);
        sb.append(nextChar);
        if (sb.toString().compareTo("26") <= 0) {
            return true;
        }
        return false;
    }
}
