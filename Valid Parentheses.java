/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> parenthStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '(' || curChar == '[' || curChar == '{') {
                parenthStack.push(curChar);
            }
            else {
                boolean isValid = checkIfValid(parenthStack, curChar);
                if (isValid == false) {
                    return false;
                }
            }
        }
        if (parenthStack.size() > 0) {
            return false;
        }
        return true;
    }
    
    private boolean checkIfValid(Stack<Character> parenthStack, char curChar) {
        if (parenthStack.size() == 0) {
            return false;
        }
        char topChar = parenthStack.pop();
        if (curChar == ')') {
            if (topChar == '(') {
                return true;
            }
        }
        else if (curChar == ']') {
            if (topChar == '[') {
                return true;
            }
        }
        else if (curChar == '}') {
            if (topChar == '{') {
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }
}
