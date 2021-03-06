/*
Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
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
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] result = {0};
        findTileCore(root, result);
        return result[0];
    }
    
    private int findTileCore(TreeNode pNode, int[] result) {
        if (pNode == null) {
            return 0;
        }
        int sum = pNode.val;
        int leftSum = findTileCore(pNode.left, result);
        int rightSum = findTileCore(pNode.right, result);
        result[0] += Math.abs(leftSum - rightSum);
        sum = sum + leftSum + rightSum;
        return sum;
    }
}
