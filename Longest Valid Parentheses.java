/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/
// The key is stack store the index which is the index of previous invalid right parenthese.
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '(') {
                stack.push(i);
            }
            else {
                if (stack.size() == 0 || s.charAt(stack.peek()) != '(') {
                    stack.push(i);
                }
                else {
                    stack.pop();
                    max = Math.max(max, i - (stack.size() == 0 ? -1 : stack.peek()));
                }
            }
        }
        return max;
    }
}
