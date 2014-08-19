/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        boolean result = hasPathSumCore(root, sum, 0);
        return result;
    }
    
    private boolean hasPathSumCore(TreeNode pNode, int sum, int curSum) {
        if (pNode == null) {
            return false;
        }
        curSum += pNode.val;
        if (curSum == sum && pNode.left == null && pNode.right == null) {
            return true;
        }
        boolean leftResult = hasPathSumCore(pNode.left, sum, curSum);
        if (leftResult == true) {
            return true;
        }
        boolean rightResult = hasPathSumCore(pNode.right, sum, curSum);
        return rightResult;
    }
}
