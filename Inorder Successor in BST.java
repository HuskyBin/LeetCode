/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
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

// Better Clean code

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null || root == null) {
            return null;
        }
        
        TreeNode successor = null;
        while (root != null) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        return successor;
    }
}


// My old code
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null || root == null) {
            return null;
        }
        
        if (p.right != null) {
            return findChildNext(p);
        }
        else {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode parentNode = findParentNode(root, p, stack);
            if (parentNode == null) {
                return null;
            }
            if (parentNode.left == p) {
                return parentNode;
            }
            else {
                TreeNode child = parentNode;
                stack.pop();
                while (stack.size() > 0 && stack.peek().right == child) {
                    child = stack.pop();
                }
                if (stack.size() == 0) return null;
                return stack.pop();
            }
        }
    }
        
        private TreeNode findChildNext(TreeNode pNode) {
            TreeNode resultNode = pNode.right;
            while (resultNode.left != null) {
                resultNode = resultNode.left;
            }
            return resultNode;
        }
        
        private TreeNode findParentNode(TreeNode root, TreeNode pNode, Stack<TreeNode> stack) {
            if (root == null) {
                return null;
            }
            if (root == pNode) {
                return null;
            }
            stack.push(root);
            
            if (root.val > pNode.val) {
                if (root.left == pNode) {
                    return root;
                }
                else {
                    return findParentNode(root.left, pNode, stack);
                }
            }
            else if (root.val < pNode.val) {
                if (root.right == pNode) {
                    return root;
                }
                else {
                    return findParentNode(root.right, pNode, stack);
                }
            }
            else {
                return null;
            }
        }
}
