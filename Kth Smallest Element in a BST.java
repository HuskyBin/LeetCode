/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
*/
public class Solution {
    
    private int count = 1;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        
        TreeNode result = kthSmallestCore(root, k);
        return result.val;
    }
    
    private TreeNode kthSmallestCore(TreeNode pNode, int k) {
        if (pNode == null) {
            return null;
        }
        TreeNode leftNode = kthSmallestCore(pNode.left, k);
        if (leftNode != null) {
            return leftNode;
        }
        if (count == k) {
            return pNode;
        }
        count++;
        return kthSmallestCore(pNode.right, k);
        
    }
}
