/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

*/

public class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<String> valueStack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String curStr = tokens[i];
            if (curStr.equals("+") || curStr.equals("-") || curStr.equals("*") || curStr.equals("/")) {
                computeOperand(curStr, valueStack);
            }
            else {
                valueStack.push(curStr);
            }
        }
        int result = Integer.valueOf(valueStack.pop());
        return result;
    }
    
    public void computeOperand(String str, Stack<String> valueStack) {
        int operandOne = Integer.valueOf(valueStack.pop());
        int operandTwo = Integer.valueOf(valueStack.pop());
        int result = 0;
        if (str.equals("+")) {
            result = operandTwo + operandOne;
        }
        else if (str.equals("-")) {
            result = operandTwo - operandOne;
        }
        else if (str.equals("*")) {
            result = operandTwo * operandOne;
        }
        else {
            result = operandTwo / operandOne;
        }
        valueStack.push(String.valueOf(result));
        return;
    }
}
