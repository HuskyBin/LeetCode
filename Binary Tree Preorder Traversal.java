/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (stack.size() > 0 || pNode != null) {
            if (pNode != null) {
                resultList.add(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            }
            else {
                TreeNode topNode = stack.pop();
                pNode = topNode.right;
            }
        }
        return resultList;
    }
}


//Another Solution 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            resultList.add(topNode.val);
            if (topNode.right != null) {
                stack.push(topNode.right);
            }
            if (topNode.left != null) {
                stack.push(topNode.left);
            }
        }
        return resultList;
    }
}
