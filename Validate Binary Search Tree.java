/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/
//In-order traversal has order
public class Solution {
    
    public TreeNode lastNode = new TreeNode(Integer.MIN_VALUE);
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean result = isValidBSTCore(root);
        return result;
    }
    
    public boolean isValidBSTCore(TreeNode pNode) {
        if (pNode == null) {
            return true;
        }
        boolean leftResult = isValidBSTCore(pNode.left);
        if (leftResult == false || pNode.val <= lastNode.val) {
            return false;
        }
        lastNode = pNode;
        boolean rightResult = isValidBSTCore(pNode.right);
        return rightResult;
    }
}
