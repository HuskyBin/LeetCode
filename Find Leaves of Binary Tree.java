/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        findLeavesCore(root, resultList, 0);
        return resultList;
    }
    
    private int findLeavesCore(TreeNode pNode, List<List<Integer>> resultList, int level) {
        if (pNode == null) {
            return -1;
        }
        int left = findLeavesCore(pNode.left, resultList, level + 1);
        int right = findLeavesCore(pNode.right, resultList, level + 1);
        int curLevel = Math.max(left, right) + 1;
        if (resultList.size() >= curLevel + 1) {
            List<Integer> curList = resultList.get(curLevel);
            curList.add(pNode.val);
        }
        else {
            List<Integer> newList = new ArrayList<>();
            newList.add(pNode.val);
            resultList.add(newList);
        }
        return curLevel;
    }
}
