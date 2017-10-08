/*
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
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
class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }
    
    private void dfs(TreeNode pNode) {
        if (pNode == null) {
            return;
        }
        dfs(pNode.right);
        pNode.val += sum;
        sum = pNode.val;
        dfs(pNode.left);
    }
}
// no gloable variable
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root, 0);
        return root;
    }
    
    private int dfs(TreeNode pNode, int sum) {
        if (pNode == null) {
            return sum;
        }
        int rightValue = dfs(pNode.right, sum);
        rightValue += pNode.val;
        pNode.val = rightValue;
        return dfs(pNode.left, rightValue);
    }
}
