/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
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
    public int maxLength = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return maxLength;
        }
        findDiameterOfBinaryTree(root);
        return maxLength - 1;
    }
    
    private int findDiameterOfBinaryTree(TreeNode pNode) {
        if (pNode == null) {
            return 0;
        }
        int leftLength = findDiameterOfBinaryTree(pNode.left);
        int rightLength = findDiameterOfBinaryTree(pNode.right);
        maxLength = Math.max(maxLength, leftLength + rightLength + 1);
        return Math.max(leftLength, rightLength) + 1;
    }
}
