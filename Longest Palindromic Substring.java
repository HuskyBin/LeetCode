/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int len = 2; len <= s.length(); len++) {
            for (int start = 0; start + len - 1 < s.length(); start++) {
                int end = start + len - 1;
                if (len == 2) {
                    if (s.charAt(start) == s.charAt(end)) {
                        isPalindrome[start][end] = true;
                    }
                }
                else {
                    if (s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1] == true) {
                        isPalindrome[start][end] = true;
                    }
                }
            }
        }
        
        int  maxLength = Integer.MIN_VALUE;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome[i][j] && (j - i + 1) > maxLength) {
                    startIndex = i;
                    endIndex = j;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }
}
