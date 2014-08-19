/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/
//Recursion method
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        connectCore(root, null);
    }
    
    private void connectCore(TreeLinkNode pNode, TreeLinkNode parentNode) {
        if (pNode == null) {
            return;
        }
        if (parentNode != null) {
            pNode.next = findNextNode(parentNode, pNode);
        }
        connectCore(pNode.right, pNode);
        connectCore(pNode.left, pNode);
    }
    
    private TreeLinkNode findNextNode(TreeLinkNode parentNode, TreeLinkNode curNode) {
        if (parentNode.left == curNode && parentNode.right != null) {
            return parentNode.right;
        }
        TreeLinkNode pNode = parentNode.next;
        while (pNode != null) {
            if (pNode.left != null) {
                return pNode.left;
            }
            if (pNode.right != null) {
                return pNode.right;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
