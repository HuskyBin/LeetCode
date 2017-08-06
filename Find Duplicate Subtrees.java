/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.
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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> resultList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        postOrder(root, map, resultList);
        return resultList;
    }
    
    private String postOrder(TreeNode pNode, Map<String, Integer> map, List<TreeNode> resultList) {
        if (pNode == null) {
            return "#";
        }
        String serialStr = pNode.val + "," + postOrder(pNode.left, map, resultList) + "," + postOrder(pNode.right, map, resultList);
        if (map.containsKey(serialStr) && map.get(serialStr) == 1) {
            resultList.add(pNode);
        }
        map.put(serialStr, map.getOrDefault(serialStr, 0) + 1);
        return serialStr;
    }
    
}
