/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.


*/
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return (q == null) ? true : false;
        }
        if (q == null) {
            return false;
        }
        boolean result = isSameTreeCore(p, q);
        return result;
    }
    
    private boolean isSameTreeCore(TreeNode treeOne, TreeNode treeTwo) {
        if (treeOne == null && treeTwo == null) {
            return true;
        }
        if (treeOne == null || treeTwo == null) {
            return false;
        }
        if (treeOne.val != treeTwo.val) {
            return false;
        }
        boolean leftResult = isSameTreeCore(treeOne.left, treeTwo.left);
        if (leftResult == false) {
            return false;
        }
        boolean rightResult = isSameTreeCore(treeOne.right, treeTwo.right);
        return rightResult;
    }
}
