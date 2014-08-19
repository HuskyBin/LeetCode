/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/

// find out that two disordered nodes. And then swap their values.
public class Solution {
    public TreeNode lastNode = new TreeNode(Integer.MIN_VALUE);
    public TreeNode firstNode;
    public TreeNode secondNode;
    
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return ;
        }
        traversal(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
        return;
    }
    
    public void traversal(TreeNode pNode) {
        if (pNode == null) {
            return;
        }
        traversal(pNode.left);
        if (firstNode == null && pNode.val <= lastNode.val) {
            firstNode = lastNode;
        }
        if (pNode.val <= lastNode.val) {
            secondNode = pNode;
        }
        lastNode = pNode;
        traversal(pNode.right);
    }
}
