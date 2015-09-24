/*
implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

public class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<String> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '(') {
                stack.push(String.valueOf(curChar));
            }
            else if (Character.isDigit(curChar)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(s.charAt(i));
                    i++;
                }
                i--;
                stack.push(String.valueOf(num));
            }
            else if (curChar == '+' || curChar == '-') {
                stack.push(String.valueOf(curChar));
            }
            else if (curChar == ')') {
                int tmp = 0;
                while (true) {
                    int numOne = Integer.valueOf(stack.pop());
                    if (stack.peek().equals("(")) {
                        tmp += numOne;
                        stack.pop();    
                        stack.push(String.valueOf(tmp));
                        break;
                    }
                    else {
                        String op = stack.pop();
                        if (op.equals("+")) {
                            tmp += numOne;
                        }
                        else {
                            tmp -= numOne;
                        }
                    }
                }
            }
        }
        while (stack.size() > 0) {
            int numOne = Integer.valueOf(stack.pop());
            if (stack.size() == 0) {
                result += numOne;
                break;
            }
            else {
                String op = stack.pop();
                if (op.equals("+")) {
                    result += numOne;
                }
                else {
                    result -= numOne;
                }
            }
        }
        return result;
    }
}


