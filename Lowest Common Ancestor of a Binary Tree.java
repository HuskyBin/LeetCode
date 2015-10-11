/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
*/
class Result {
    public TreeNode node;
    public boolean isAncestor;
    
    public Result(TreeNode node, boolean isAncestor) {
        this.node = node;
        this.isAncestor = isAncestor;
    }
} 
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        
        Result result = lowestCommonCore(root, p, q);
        if (result.isAncestor == true) {
            return result.node;
        }
        return null;
    }
    
    private Result lowestCommonCore(TreeNode pNode, TreeNode p, TreeNode q){
        if (pNode == null){
            return new Result(null, false);
        }
        if (pNode == p && pNode == q) {
            return new Result(pNode, true);
        }
        
        Result left = lowestCommonCore(pNode.left, p, q);
        if (left.isAncestor) {
            return left;
        }
        Result right = lowestCommonCore(pNode.right, p, q);
        if (right.isAncestor) {
            return right;
        }
        
        if (left.node != null && right.node != null) {
            return new Result(pNode, true);
        }
        else if (pNode == p || pNode == q){
            return new Result(pNode, (left.node != null || right.node != null) ? true : false);
        }
        if (left.node != null) {
            return left;
        }
        else {
            return right;
        }
    }
    
    
    
}
