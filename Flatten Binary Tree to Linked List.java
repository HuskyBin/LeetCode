/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*/
public class Solution {
    
    private TreeNode lastNode = null;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode parentNode = null;
        TreeNode lastNode = null;
        flattenCore(root, parentNode);
    }
    
    private void flattenCore(TreeNode pNode, TreeNode parentNode) {
        if (pNode == null) {
            return;
        }
        TreeNode rightChild = pNode.right;
        if (parentNode != null && parentNode.left == pNode) {
            parentNode.left = null;
        }
        if (lastNode != null) {
            lastNode.right = pNode;
        }
        lastNode = pNode;
        flattenCore(pNode.left, pNode);
        flattenCore(rightChild, pNode);
    }
}
