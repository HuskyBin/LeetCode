/*
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode topNode = queue.poll();
                if (max < topNode.val) {
                    max = topNode.val;
                } 
                if (topNode.left != null) {
                    queue.add(topNode.left);
                }
                if (topNode.right != null) {
                    queue.add(topNode.right);
                }
            }
            resultList.add(max);
        }
        return resultList;
    }
}
