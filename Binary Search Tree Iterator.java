/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    /** @return the next smallest number */
    public int next() {
        if (stack.size() == 0) throw new RuntimeException();
        TreeNode topNode = stack.pop();
        if (topNode.right != null) {
            TreeNode pNode = topNode.right;
            stack.push(pNode);
            while (pNode.left != null) {
                stack.push(pNode.left);
                pNode = pNode.left;
            }
        }
        return topNode.val;
    }
}
