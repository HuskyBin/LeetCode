/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict == null || s.length() == 0) {
            return false;
        }
        int[] dp = new int[s.length()];
        boolean result = wordBreakCore(s, dict, dp, 0);
        return result;
    }
    
    public boolean wordBreakCore(String s, Set<String> dict, int[] dp, int index) {
        if (index == s.length()) {
            return true;
        }
        if (dp[index] != 0) {
            if (dp[index] == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        boolean subResult = false;
        for (int i = index + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(index, i))) {
                subResult |= wordBreakCore(s, dict, dp, i);
                if (subResult == true) {
                    break;
                }
            }
        }
        if (subResult == true) {
            dp[index] = 1;
        }
        else {
            dp[index] = -1;
        }
        return subResult;
    }
}
