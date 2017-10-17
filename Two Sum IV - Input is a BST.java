/*
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
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
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        return findTarget(root, set, k);
    }
    
    private boolean findTarget(TreeNode pNode, Set<Integer> set, int target) {
        if (pNode == null) {
            return false;
        }
        if (set.contains(target - pNode.val)) {
            return true;
        }
        set.add(pNode.val);
        if (findTarget(pNode.left, set, target)) {
            return true;
        }
        return findTarget(pNode.right, set, target);
    }
}
// Best solution time O(n), space O(Logn)
If I have to choose one from them, I would prefer the third one because it takes advantage of the property of BST and is with lower space complexity in average. Its code is shown below.

    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
    	Stack<TreeNode> l_stack = new Stack<>();
    	Stack<TreeNode> r_stack = new Stack<>();
    	stackAdd(l_stack, root, true);
    	stackAdd(r_stack, root, false);
    	while(l_stack.peek() != r_stack.peek()){
    	    int n = l_stack.peek().val + r_stack.peek().val;
    	    if(n == k){
    		return true;
    	    }else if(n > k){
    		stackNext(r_stack, false);
    	    }else{
		stackNext(l_stack, true);
    	    }
    	}
    	return false;
    }
    
    private void stackAdd(Stack<TreeNode> stack, TreeNode node, boolean isLeft){
    	while(node != null){
    	    stack.push(node);
            node = (isLeft) ? node.left : node.right;
    	}
    }

    private void stackNext(Stack<TreeNode> stack, boolean isLeft){
    	TreeNode node = stack.pop();
    	if(isLeft){
    	    stackAdd(stack, node.right, isLeft);
    	}else{
    	    stackAdd(stack, node.left, isLeft);
    	}
    }
