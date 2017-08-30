/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
class Solution {
    public int result = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> pathSumMap = new HashMap<>();
        pathSumMap.put(0, 1);
        pathSumCore(root, sum, pathSumMap, 0);
        return result;
    }

    private void pathSumCore(TreeNode pNode, int sum, Map<Integer, Integer> pathSumMap, int curSum) {
        if (pNode == null) {
            return;
        }
        curSum += pNode.val;
        if (pathSumMap.containsKey(curSum - sum)) {
            result += pathSumMap.get(curSum - sum);
        }
        if (pathSumMap.containsKey(curSum)) {
            pathSumMap.put(curSum, pathSumMap.get(curSum) + 1);
        }
        else {
            pathSumMap.put(curSum, 1);
        }
        pathSumCore(pNode.left, sum, pathSumMap, curSum);
        pathSumCore(pNode.right, sum, pathSumMap, curSum);
        if (pathSumMap.get(curSum) == 1) {
            pathSumMap.remove(curSum);
        }
        else {
            pathSumMap.put(curSum, pathSumMap.get(curSum) - 1);
        }
    }
}
