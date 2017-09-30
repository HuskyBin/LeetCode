/*
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
*/
class Solution {
    public String originalDigits(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == 'z') {
                count[0]++;
            }
            else if (curChar == 'w') {
                count[2]++;
            }
            else if (curChar == 'u') {
                count[4]++;
            }
            else if (curChar == 'x') {
                count[6]++;
            }
            else if (curChar == 'g') {
                count[8]++;
            }
            else if (curChar == 'o') {
                count[1]++;
            }
            else if (curChar == 'r') {
                count[3]++;
            }
            else if (curChar == 'f') {
                count[5]++;
            }
            else if (curChar == 'v') {
                count[7]++;
            }
            else if (curChar == 'i') {
                count[9]++;
            }
        }
        
        count[1] = count[1] - count[0] - count[2] - count[4];
        count[3] = count[3] - count[0] - count[4];
        count[5] = count[5] - count[4];
        count[7] = count[7] - count[5];
        count[9] = count[9] - count[5] - count[6] - count[8];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            while (count[i] > 0) {
                sb.append(i);
                count[i]--;
            }
        }
        return sb.toString();
    }
}
