/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int[][] dp = new int[s.length()][p.length()];
        boolean result = isMathCore(s, 0, p, 0, dp);
        return result;
    }
    
    private boolean isMathCore(String s, int sIndex, String p, int pIndex, int[][] dp) {
        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        }
        if (pIndex == p.length() && sIndex < s.length()) {
            return false;
        }
        if (pIndex < p.length() && sIndex == s.length()) {
            boolean isValid = checkIfValid(p, pIndex);
            return isValid;
        }
        if (dp[sIndex][pIndex] != 0) {
            if (dp[sIndex][pIndex] == 1) {
                return true;
            } 
            else {
                return false;
            }
        }
        boolean subResult = false;
        if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
            if (p.charAt(pIndex) == '.') {
                for (int i = sIndex; i <= s.length(); i++) {
                    subResult |= isMathCore(s, i, p, pIndex + 2, dp);
                    if (subResult == true) {
                        break;
                    }
                }
            }
            else{
                int i = sIndex;
                subResult |= isMathCore(s, i, p, pIndex + 2, dp);
                while (i < s.length() && s.charAt(i) == p.charAt(pIndex)) {
                    i++;
                    subResult |= isMathCore(s, i, p, pIndex + 2, dp);
                    if (subResult == true) {
                        break;
                    }
                }
            }
        }
        else {
            if (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex)) {
                subResult |= isMathCore(s, sIndex + 1, p, pIndex + 1, dp);
            }
        }
        dp[sIndex][pIndex] = subResult == true ? 1 : 0;
        return subResult;
    }
    
    private boolean checkIfValid(String s, int index) {
        while (index < s.length() - 1 && s.charAt(index + 1) == '*') {
            index += 2;
        }
        if (index == s.length()) {
            return true;
        }
        return false;
    }
}
