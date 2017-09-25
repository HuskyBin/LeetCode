/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
*/
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        } 
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar != endChar) {
                return checkHelper(s, start + 1, end) || checkHelper(s, start, end - 1);
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    private boolean checkHelper(String s, int start, int end) {
        while (start <= end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar != endChar) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
