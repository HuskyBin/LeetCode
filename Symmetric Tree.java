/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
*/
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean result = isSymmetricCore(root, root);
        return result;
    }
    
    private boolean isSymmetricCore(TreeNode pNode1, TreeNode pNode2) {
        if (pNode1 == null && pNode2 == null) {
            return true;
        }
        if (pNode1 == null || pNode2 == null) {
            return false;
        }
        if (pNode1.val != pNode2.val) {
            return false;
        }
        boolean subResult = isSymmetricCore(pNode1.left, pNode2.right);
        if (subResult == false) {
            return false;
        }
        subResult = isSymmetricCore(pNode1.right, pNode2.left);
        return subResult;
    }
}
