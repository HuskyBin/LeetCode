/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

public class Solution {
     public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String str = '+' + s + "+";

        int index = 0;
        int total = 0;
        int term = 0;
        char op;
        int n = 0;

        while (index < str.length()) {
            index = getNextNoEmptyChar(str, index);
            op = str.charAt(index);
            index++;
            if (op == '+' || op == '-') {
                total += term;
                if (index == str.length()) {
                    break;
                }
                index = getNextNoEmptyChar(str, index);
                String subString = getSubStringNum(str, index);
                index += subString.length();
                term = Integer.parseInt(subString);
                if (op == '-') {
                    term *= -1;
                }
            }
            else {  
                index = getNextNoEmptyChar(str, index);
                String subString = getSubStringNum(str, index);
                index += subString.length();
                n = Integer.parseInt(subString);
                if (op == '*') {
                    term *= n;
                }
                else {
                    term /= n;
                }
            }
        }
        return total;
    }

    private String getSubStringNum(String str, int index) {
        int start = index;
        int end = start + 1;
        while (end < str.length()) {
            if (str.charAt(end) >= '0' && str.charAt(end) <= '9') {
                end++;
            }
            else {
                break;
            }
        }
        return str.substring(start, end);
    }


    private int getNextNoEmptyChar(String str, int index) {
        while (index < str.length()) {
            if (str.charAt(index) == ' ') {
                index++;
            }
            else {
                return index;
            }
        }
        return -1;  
    }
}
