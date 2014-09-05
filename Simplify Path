/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/
public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        int startIndex = 1;
        while (startIndex < path.length()) {
            while (startIndex < path.length()) {
                if (path.charAt(startIndex) != '/' && path.charAt(startIndex - 1) == '/') {
                    break;
                }
                startIndex++;
            }
            if (startIndex == path.length()) {
                break;
            }
            int endIndex = startIndex;
            while (endIndex < path.length() && path.charAt(endIndex) != '/') {
                endIndex++;
            }
            String subStr = path.substring(startIndex, endIndex);
            startIndex = endIndex;
            if (subStr.equals(".")) {
                continue;
            }
            else if (subStr.equals("..")) {
                if (stack.size() != 0) {
                    stack.pop();
                }
            }
            else {
                stack.push(subStr);
            }
        }
        String result = getResult(stack);
        return result;
    }
    
    private String getResult(Stack<String> stack) {
        Stack<String> reverseStack = new Stack<>();
        while (stack.size() > 0) {
            reverseStack.push(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        while (reverseStack.size() > 0) {
            sb.append(reverseStack.pop());
            if (reverseStack.size() > 0) {
                sb.append("/");
            }
        }
        return sb.toString();
    }
}
