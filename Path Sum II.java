/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        pathSumCore(root, sum, 0, resultList, singleList);
        return resultList;
    }
    
    private void pathSumCore(TreeNode pNode, int sum, int curSum, List<List<Integer>> resultList, List<Integer> singleList) {
        if (pNode == null) {
            return;
        }
        curSum += pNode.val;
        singleList.add(pNode.val);
        if (curSum == sum && pNode.left == null && pNode.right == null) {
            List<Integer> copyList = new ArrayList<>(singleList);
            resultList.add(copyList);
        }
        pathSumCore(pNode.left, sum, curSum, resultList, singleList);
        pathSumCore(pNode.right, sum, curSum, resultList, singleList);
        singleList.remove(singleList.size() - 1); 
    }
}
