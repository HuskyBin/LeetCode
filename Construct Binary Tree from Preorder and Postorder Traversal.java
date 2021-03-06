/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            indexMap.put(post[i], i);
        }
        return dfsCore(pre, post, 0, pre.length - 1, 0, post.length - 1, indexMap);
    }
    
    private TreeNode dfsCore(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return node;
        }
        int index = map.get(pre[preStart + 1]);
        int leftIndex = index - postStart + 1;
        node.left = dfsCore(pre, post, preStart + 1, preStart + leftIndex,
                           postStart, postStart + leftIndex - 1, map);
        node.right = dfsCore(pre, post, preStart + 1 + leftIndex, preEnd, postStart + leftIndex, postEnd - 1, map);
        return node;
    }
}
