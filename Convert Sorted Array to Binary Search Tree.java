/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        TreeNode rootNode = sortedArrayToBSTCore(num, 0, num.length - 1);
        return rootNode;
    }
    
    private TreeNode sortedArrayToBSTCore(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = start + (end - start) / 2;
        TreeNode pNode = new TreeNode(num[middle]);
        pNode.left = sortedArrayToBSTCore(num, start, middle - 1);
        pNode.right = sortedArrayToBSTCore(num, middle + 1, end);
        return pNode;
    }
}
