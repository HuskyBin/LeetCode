/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
Credits:
Special thanks to @hpplayer for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> resultList = new ArrayList<>();
        if (s == null) {
            return resultList;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean done = false;
        while (queue.size() > 0) {
            String top = queue.poll();
            if (isValid(top)) {
                resultList.add(top);
                done = true;
            }
            if (done == true) {
                continue;
            }
            for (int i = 0; i < top.length(); i++) {
                String substring = top.substring(0, i) + top.substring(i + 1);
                if ((top.charAt(i) == '(' || top.charAt(i) == ')') && !visited.contains(substring)) {
                    queue.add(substring);
                    visited.add(substring);
                }
            }
        }
        return resultList;
    }
    
    private boolean isValid(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '(') {
                sum++;
            }
            else if (curChar == ')') {
                sum--;
            }
            if (sum < 0) {
                return false;
            }
        }
        return sum == 0;
    }
}
