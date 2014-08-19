/*
Given inorder and postorder traversal of a tree, construct the binary tree.

*/
public class Solution {
    private int postOrderIndex = 0;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        postOrderIndex = postorder.length - 1;
        TreeNode rootNode = buildTreeCore(inorder, 0, inorder.length - 1, postorder);
        return rootNode;
    }
    
    private TreeNode buildTreeCore(int[] inorder, int inStart, int inEnd, int[] postorder) {
        if (inStart > inEnd || postOrderIndex == -1) {
            return null;
        }
        TreeNode pNode = new TreeNode(postorder[postOrderIndex]);
        int pNodeInorderIndex = findpNodeInInorder(inorder, inStart, inEnd, postorder[postOrderIndex]);
        postOrderIndex--;
        pNode.right = buildTreeCore(inorder, pNodeInorderIndex + 1, inEnd, postorder);
        pNode.left = buildTreeCore(inorder, inStart, pNodeInorderIndex - 1, postorder);
        return pNode;
    }
    
    private int findpNodeInInorder(int[] inorder, int start, int end, int target) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
