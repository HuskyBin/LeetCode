/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/
public class Solution {
    public int preOrderIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        
        TreeNode rootNode = buildTreeCore(preorder, inorder, 0, inorder.length - 1);
        return rootNode;
    }
    
    private TreeNode buildTreeCore(int[] preorder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd || preOrderIndex == preorder.length) {
            return null;
        }
        TreeNode pNode = new TreeNode(preorder[preOrderIndex]);
        int nodeInOrderIndex = findPreOrderIndex(inorder, inStart, inEnd, preorder[preOrderIndex]);
        preOrderIndex++;
        pNode.left = buildTreeCore(preorder, inorder, inStart, nodeInOrderIndex - 1);
        pNode.right = buildTreeCore(preorder, inorder, nodeInOrderIndex + 1, inEnd);
        return pNode;
    }
    
    private int findPreOrderIndex(int[] inorder, int start, int end, int target) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
