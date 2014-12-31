/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char curChar = s.charAt(i);
            if (curChar < 'A' || curChar > 'Z') {
                return -1;
            }
            int number = (curChar - 'A') + 1;
            result += (int)Math.pow(26, (s.length() - 1 - i)) * number;
        }
        return result;
    }
}
