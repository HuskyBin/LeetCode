/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
*/
public class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        int i = 0;
        int e = len - 1;
        while (i <= e && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        if (i > e) {
            return false;
        }
        while (e >= i && Character.isWhitespace(s.charAt(e))) {
            e--;
        }
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        boolean num = false;
        boolean dot = false;
        boolean exp = false;
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = true;
            }
            else if (c == '.') {
                if (exp || dot) {
                    return false;
                }
                dot = true;
            }
            else if (c == 'e') {
                if (num == false || exp == true) {
                    return false;
                }
                num = false;
                exp = true;
            }
            else if (c == '+' || c == '-') {
                if (s.charAt(i - 1) != 'e') {
                    return false;
                }
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
}
