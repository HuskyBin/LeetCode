/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Show Hint 
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
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
    
    private class Result {
        public int min;
        public int max;
        public boolean isValid;
        public int size;
        
        public Result(int min, int max, boolean isValid, int size) {
            this.min = min;
            this.max = max;
            this.isValid = isValid;
            this.size = size;
        }
    } 
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Result result = findLargest(root);
        return result.size;
    }
    
    private Result findLargest(TreeNode pNode) {
        if (pNode == null) {
            return null;
        }
        
        if (pNode.left == null && pNode.right == null) {
            return new Result(pNode.val, pNode.val, true, 1);
        }
        Result leftResult = findLargest(pNode.left);
        Result rightResult = findLargest(pNode.right);
       if (leftResult != null && rightResult != null) {
           if (leftResult.isValid == false || rightResult.isValid == false) {
               return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, Math.max(leftResult.size, rightResult.size));
           }
           else {
               if (pNode.val >= leftResult.max && pNode.val <= rightResult.min) {
                   return new Result(leftResult.min, rightResult.max, true, leftResult.size + rightResult.size + 1);
               }
               else {
                   return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, Math.max(leftResult.size, rightResult.size));
               }
           }
       }
       else if (leftResult != null) {
           if (leftResult.isValid == false) {
               return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, leftResult.size);
           }
           else if (pNode.val >= leftResult.max) {
               return new Result(leftResult.min, pNode.val, true, leftResult.size + 1);
           }
           else {
               return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, leftResult.size);
           }
       }
       else {
           if (rightResult.isValid == false) {
               return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, rightResult.size);
           }
           else if (pNode.val <= rightResult.min) {
               return new Result(pNode.val, rightResult.max, true, rightResult.size + 1);
           }
           else {
               return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false, rightResult.size);
           }
       }
    }
}
