/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*/
public class Solution {
    public int maxValue = 0;
    
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        findLongestCore(root.left, root, 1);
        findLongestCore(root.right, root, 1);
        return maxValue;
    }
    
    private void findLongestCore(TreeNode pNode, TreeNode parentNode, int curLength) {
        if (curLength > maxValue) {
            maxValue = curLength;
        }
        if (pNode == null) {
            return;
        }
        if (parentNode.val + 1 == pNode.val) {
            findLongestCore(pNode.left, pNode, curLength + 1);
            findLongestCore(pNode.right, pNode, curLength + 1);
        }
        else {
            findLongestCore(pNode.left, pNode, 1);
            findLongestCore(pNode.right, pNode, 1);
        }
    }
}
