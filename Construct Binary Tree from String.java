/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int firstValueIndex = findFirstValueIndex(s);
        String firstValue = s.substring(0, firstValueIndex);
        // String restStr = s.substring(firstValueIndex);
        int leftPartEndIndex = findLeftPartEndIndex(s);
        String leftPare = null;
        if (firstValueIndex + 1 < leftPartEndIndex) {
            leftPare = s.substring(firstValueIndex + 1, leftPartEndIndex);
        }
        String rightPare = null;
        if (leftPartEndIndex + 2 < s.length() - 1) {
            rightPare = s.substring(leftPartEndIndex + 2, s.length() - 1);
        }
        TreeNode newNode = new TreeNode(Integer.valueOf(firstValue));
        newNode.left = str2tree(leftPare);
        newNode.right = str2tree(rightPare);
        return newNode;
    }
    
    private int findLeftPartEndIndex(String str) {
        int leftCount = 0;
        int rightCount = 0;
        int index = 0;
        while (index < str.length()) {
            char curChar = str.charAt(index);
            if (curChar == '(') {
                leftCount++;
            }
            else if (curChar == ')') {
                rightCount++;
                if (rightCount == leftCount) {
                    break;
                }
            }
            index++;
        }
        return index;
    }
    
    private int findFirstValueIndex(String str) {
        int start = 0; 
        while (start < str.length()) {
            if ((str.charAt(start) >= '0' && str.charAt(start) <= '9') || str.charAt(start) == '-') {
                start++;
            }
            else {
                break;
            }
        }
        return start;
    }
}
