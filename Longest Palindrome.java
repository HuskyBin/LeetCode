/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/
public class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int oddNumber = 0;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (!map.containsKey(curChar)) {
                map.put(curChar, 0);
            }
            map.put(curChar, map.get(curChar) + 1);
            if (map.get(curChar) % 2 == 0) {
                total += 2;
                oddNumber--;
            }
            else {
                oddNumber++;
            }
        }
        if (oddNumber > 0) { 
            total++;
        }
        return total;
    }
}
