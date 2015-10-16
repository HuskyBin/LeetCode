/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        
        StringBuilder onePath = new StringBuilder();
        binaryTreePathCore(root, onePath, resultList);
        return resultList;
    }
    
    private void binaryTreePathCore(TreeNode pNode, StringBuilder onePath, List<String> resultList) {
        if (pNode == null) {
            return;
        }
        
        int length = String.valueOf(pNode.val).length();
        onePath.append(pNode.val);
        if (pNode.left == null && pNode.right == null) {
            resultList.add(onePath.toString());
            
        }
        else {
            onePath.append("->");
            length += 2;
            if (pNode.left != null)  binaryTreePathCore(pNode.left, onePath, resultList);
            if (pNode.right != null) binaryTreePathCore(pNode.right, onePath, resultList);
        }
    
        
        onePath.setLength(onePath.length() - length);
    }
    
}
