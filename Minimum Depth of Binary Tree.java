/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/
public class Solution {
    public int minValue = Integer.MAX_VALUE;
    public int curDepth = 1;
    
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        minDepthCore(root);
        return minValue;
    }
    
    public void minDepthCore(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (curDepth < minValue) {
                minValue = curDepth;
            }
            return;
        }
 
        curDepth++;
        minDepthCore(root.left);
        minDepthCore(root.right);
        curDepth--;
    }
}
