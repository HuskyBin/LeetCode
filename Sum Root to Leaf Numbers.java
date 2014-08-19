/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/
public class Solution {
    
    public int sum = 0;
    
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumNumbersCore(root, 0);
        return sum;
    }
    
    private void sumNumbersCore(TreeNode pNode, int curNum) {
        if (pNode == null) {
            return;
        }
        curNum = curNum * 10 + pNode.val;
        if (pNode.left == null && pNode.right == null) {
            sum += curNum;
        }
        sumNumbersCore(pNode.left, curNum);
        sumNumbersCore(pNode.right, curNum);
    }
}
