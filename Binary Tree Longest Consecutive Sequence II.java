/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
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
class Solution {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, root);
        return max;
    }
    
    private int[] dfs(TreeNode pNode, TreeNode parentNode) {
        if (pNode == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dfs(pNode.left, pNode);
        int[] right = dfs(pNode.right, pNode);
        max = Math.max(left[0] + right[1] + 1, max);
        max = Math.max(left[1] + right[0] + 1, max);
        int inc = 0;
        int dec = 0;
        if (pNode.val == parentNode.val + 1) {
            inc = Math.max(left[0], right[0]) + 1;
        }
        else if (pNode.val + 1 == parentNode.val) {
            dec = Math.max(left[1], right[1]) + 1;
        }
        return new int[] {inc, dec};
    }
}
