/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
*/
// DFS
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
    public int result = 1;
    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, Integer> max = new HashMap<>();
        Map<Integer, Integer> min = new HashMap<>(); 
        max.put(0, 1);
        min.put(0, 1);
        dfs(root, max, min, 0, 1);
        return result;
    }
    
    private void dfs(TreeNode pNode, Map<Integer, Integer> max, Map<Integer, Integer> min, int level, int value) {
        if (pNode == null) {
            return;
        }
        if (max.containsKey(level)) {
            max.put(level, Math.max(value, max.get(level)));
        }
        else {
            max.put(level, value);
        }
        if (min.containsKey(level)) {
            min.put(level, Math.min(value, min.get(level)));
        }
        else {
            min.put(level, value);
        }
        result = Math.max(result, max.get(level) - min.get(level) + 1);
        dfs(pNode.left, max, min, level + 1, 2 * value);
        dfs(pNode.right, max, min, level + 1, 2 * value + 1);
    }
}

// BFS
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

    public int widthOfBinaryTree(TreeNode root) {
        int max = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> count = new LinkedList<>();
        queue.add(root);
        count.add(1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int left = 0;
            int right = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                int index = count.poll();
                if (i == 0) {
                    left = index;
                }
                if (i == size - 1) {
                    right = index;
                }
                if (curNode.left != null) {
                    queue.add(curNode.left);
                    count.add(2 * index);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                    count.add(2 * index + 1);
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

}
