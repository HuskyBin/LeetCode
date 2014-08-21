/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// Use a PreNode to represent the previous node and using the relationship between preNode with curNode to determin which node
// should be add into stack, or if it should pop node from stack
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode preNode = null;
        TreeNode pNode = root;
        stack.push(pNode);
        while (stack.size() > 0) {
            TreeNode curNode = stack.peek();
            if (preNode != null && (preNode == curNode || preNode.left == curNode || preNode.right == curNode)) {
                if (curNode.left != null) {
                    stack.push(curNode.left);
                }
                else if (curNode.right != null) {
                    stack.push(curNode.right);
                }
                else {
                    resultList.add(curNode.val);
                    stack.pop();
                }
            }
            else if (preNode != null && curNode.left == preNode) {
                if (curNode.right != null) {
                    stack.push(curNode.right);
                }
                else {
                    resultList.add(curNode.val);
                    stack.pop();
                }
            }
            else if(preNode != null && curNode.right == preNode){
                resultList.add(curNode.val);
                stack.pop();
            }
            preNode = curNode;
        }
        return resultList;
    }
}
