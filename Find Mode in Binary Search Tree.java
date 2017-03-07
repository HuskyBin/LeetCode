/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    public TreeNode preNode = null;
    public int maxFre = 0;
    public int curFre = 0;
    public List<Integer> resultList = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        checkCore(root);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    private void checkCore(TreeNode pNode) {
        if (pNode == null) return;
        checkCore(pNode.left);
        if (preNode != null) {
            if (preNode.val != pNode.val) {
                curFre = 1;
            }
            else {
                curFre ++;
            }
            if (curFre > maxFre) {
                maxFre = curFre;
                resultList.clear();
                resultList.add(pNode.val);
            }
            else if (curFre == maxFre) {
                resultList.add(pNode.val);
            }
        }
        else {
            maxFre = 1;
            curFre = 1;
            resultList.add(pNode.val);
        }
        preNode = pNode;
        checkCore(pNode.right);
    }
}
