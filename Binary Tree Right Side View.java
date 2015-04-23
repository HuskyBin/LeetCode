/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int maxDepth = -1;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        getAllRightSideViewValue(root, resultList, 0);
        return resultList;
    }

    private void getAllRightSideViewValue(TreeNode pNode, List<Integer> resultList, int curDepth) {
        if (pNode == null) {
            return;
        }
        if (curDepth > maxDepth) {
            maxDepth = curDepth;
            resultList.add(pNode.val);
        }
        getAllRightSideViewValue(pNode.right, resultList, curDepth + 1);
        getAllRightSideViewValue(pNode.left, resultList, curDepth + 1);
    }
}


// Second Solution use BFS:  each level: the last element should be appeared in right side view;
