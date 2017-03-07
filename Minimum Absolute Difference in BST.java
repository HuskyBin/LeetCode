/*
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.
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
    public int result = Integer.MAX_VALUE;
    public TreeNode preNode = null;
    public int getMinimumDifference(TreeNode root) {
        checkCore(root);
        return result;
    }
    
    private void checkCore(TreeNode pNode) {
        if (pNode == null) return;
        checkCore(pNode.left);
        if (preNode != null) {
            result = Math.min(result, pNode.val - preNode.val);
        }
        preNode = pNode;
        checkCore(pNode.right);
    }
}
