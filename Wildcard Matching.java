/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sIndex = 0;
        int pIndex = 0;
        boolean hasStar = false;
        int preSIndex = 0;
        int prePIndex = 0;
        while (sIndex < s.length()) {
            if (pIndex < p.length() && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))) {
                sIndex++;
                pIndex++;
            }
            else if (pIndex < p.length() && (p.charAt(pIndex) == '*')) {
                hasStar = true;
                preSIndex = sIndex;
                prePIndex = pIndex;
                pIndex++;
            }
            else if (hasStar == true) {
                sIndex = preSIndex + 1;
                preSIndex++;
                pIndex = prePIndex + 1;
            }
            else {
                return false;
            }
        }
        while (pIndex < p.length() && p.charAt(pIndex) == '*') {
            pIndex++;
        }
        return pIndex == p.length() ? true : false;
    }
}
