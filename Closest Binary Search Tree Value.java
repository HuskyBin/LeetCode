/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
    private int result = -1;
    public int closestValue(TreeNode root, double target) {
        result = root.val;
        closetCore(root, target);
        return result;
    }
    
    private void closetCore(TreeNode pNode, double target) {
        if (pNode == null) return;
        if (Math.abs(pNode.val - target) < Math.abs(target - result)) {
            result = pNode.val;
        }
        if (pNode.val == target) {
            return;
        }
        else if (pNode.val > target) {
            closetCore(pNode.left, target);
        }
        else {
            closetCore(pNode.right, target);
        }
    }
}
