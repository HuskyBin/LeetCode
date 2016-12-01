/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/
public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() <= 1) {
            return false;
        }
        char firstChar = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == firstChar) {
                if (checkIfValid(str, i) == true) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkIfValid(String str, int length) {
        if (str.length() % length != 0) {
            return false;
        }
        int index = 0;
        while (index < length) {
            char curChar = str.charAt(index);
            int times = 1;
            while (index + (length * times) < str.length()) {
                if (str.charAt(index + (length * times)) != curChar) {
                    return false;
                }
                times++;
            }
            index++;
        }
        return true;
    }
}
