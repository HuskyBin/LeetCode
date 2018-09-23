/*
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:
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
    private Map<Integer, List<TreeNode>> map = new HashMap<>();
    
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> results = new ArrayList<>();
        if (map.containsKey(N)) {
            return map.get(N);
        }
        if (N == 0) {
            map.put(0, results);
        }
        else if (N == 1) {
            results.add(new TreeNode(0));
            map.put(1, results);
        } else {
            for (int num = 0; num < N; num++) {
                int rest = N - num - 1;
                for (TreeNode left : allPossibleFBT(num)) {
                    for (TreeNode right : allPossibleFBT(rest)) {
                        TreeNode newNode = new TreeNode(0);
                        newNode.left = left;
                        newNode.right = right;
                        results.add(newNode);
                    }
                }
            }
            map.put(N, results);
        }
        return map.get(N);
    }
}
