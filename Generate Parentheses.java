/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        if (n <= 0) {
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        generateParenthesisCore(n, n, sb, resultList);
        return resultList;
    }
    
    private void generateParenthesisCore(int left, int right, StringBuilder sb, List<String> resultList) {
        if (left == 0 && right == 0) {
            resultList.add(sb.toString());
            return;
        }
        if (left > 0) {
            sb.append('(');
            generateParenthesisCore(left - 1, right, sb, resultList);
            sb.setLength(sb.length() - 1);
        }
        if (right > left) {
            sb.append(')');
            generateParenthesisCore(left, right - 1, sb, resultList);
            sb.setLength(sb.length() - 1);
        }
    }
}
