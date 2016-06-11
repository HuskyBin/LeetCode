/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
confused what "
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode parentNode = null;
        return upsideDownCore(root, parentNode);
    }
    
    private TreeNode upsideDownCore(TreeNode pNode, TreeNode parentNode) {
        TreeNode rootNode = pNode;
        if (pNode.left != null) {
            rootNode = upsideDownCore(pNode.left, pNode);
        }
        
        if (parentNode != null) {
            TreeNode sibling = parentNode.right;
            pNode.left = sibling;
            pNode.right = parentNode; 
        }
        else {
            pNode.left = null;
            pNode.right = null;
        }
        
        return rootNode;
    }
}
