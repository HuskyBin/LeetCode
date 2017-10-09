/*
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

*/
class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || k <= 0) {
            return s;
        }
        int start = 0;
        StringBuilder sb = new StringBuilder();
        while (start < s.length()) {
            int end = Math.min(start + k - 1, s.length() - 1);
            reverseStr(s, sb, start, end);
            int number = k;
            start = end + 1;
            while (number > 0 && start < s.length()) {
                sb.append(s.charAt(start));
                start++;
                number--;
            }
        }
        return sb.toString();
    }
    
    private void reverseStr(String str, StringBuilder sb, int start, int end) {
        while (start <= end) {
            sb.append(str.charAt(end));
            end--;
        }
    }
}
