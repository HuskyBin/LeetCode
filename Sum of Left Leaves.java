/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumCore(root, root.left) + sumCore(root, root.right);
    }
    
    private int sumCore(TreeNode parentNode, TreeNode pNode) {
        if (pNode == null) {
            return 0;
        }
        int sum = 0;
        if (pNode.left != null) {
            sum += sumCore(pNode, pNode.left);
        }
        if (pNode.right != null) {
            sum += sumCore(pNode, pNode.right);
        }
        if (pNode.left == null && pNode.right == null) {
            if (parentNode.left == pNode) {
                sum += pNode.val;
            }
        }
        return sum;
    }
}
