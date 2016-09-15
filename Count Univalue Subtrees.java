/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
*/
public class Solution {
    class ReturnValue {
        public int value;
        public boolean result;
        
        public ReturnValue(int value, boolean result) {
            this.value = value;
            this.result = result;
        }
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        int[] result = new int[1];
        ReturnValue value = findCount(root, result);
        return result[0];
    }
    
    private ReturnValue findCount(TreeNode pNode, int[] result) {
        if (pNode == null) {
            return null;
        }
        ReturnValue left = findCount(pNode.left, result);
        ReturnValue right = findCount(pNode.right, result);
        
        if ((left == null || (left.result == true && left.value == pNode.val)) && 
           (right == null || (right.result == true && right.value == pNode.val))) {
            result[0]++;
            return new ReturnValue(pNode.val, true);
        }
        return new ReturnValue(pNode.val, false);
    }
}
