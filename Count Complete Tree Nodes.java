/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.
*/

public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int result = countNodesCore(root);
        return result;
    }
    
    private int countNodesCore(TreeNode pNode) {
        if (pNode == null) {
            return 0;
        }
        
        int leftDeep = 0;
        TreeNode leftNode = pNode;
        TreeNode rightNode = pNode;
        while (leftNode != null && rightNode != null) {
            leftNode = leftNode.left;
            rightNode = rightNode.right;
            leftDeep += 1;
        }

        if (leftNode == null && rightNode == null) {
            return (2 << (leftDeep - 1)) - 1;
        }
        
        return 1 + countNodesCore(pNode.left) + countNodesCore(pNode.right);
    }
}
