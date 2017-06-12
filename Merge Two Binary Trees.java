/*
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.

Subscribe to see which companies asked this question.
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int sum = 0;
        if (t1 != null) {
            sum += t1.val;
        }
        if (t2 != null) {
            sum += t2.val;
        }
        TreeNode newNode = new TreeNode(sum);
        TreeNode t1Left = (t1 != null) ? t1.left : null;
        TreeNode t1Right = (t1 != null) ? t1.right : null;
        TreeNode t2Left = (t2 != null) ? t2.left : null;
        TreeNode t2Right = (t2 != null) ? t2.right : null;
        newNode.left = mergeTrees(t1Left, t2Left);
        newNode.right = mergeTrees(t1Right, t2Right);
        return newNode; 
    }
}
