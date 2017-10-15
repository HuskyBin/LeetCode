/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) {
            return null;
        }
        return buildTree(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = findMaxIndex(nums, start, end);
        TreeNode pNode = new TreeNode(nums[maxIndex]);
        pNode.left = buildTree(nums, start, maxIndex - 1);
        pNode.right = buildTree(nums, maxIndex + 1, end);
        return pNode;
    }
    
    private int findMaxIndex(int[] nums, int start, int end) {
        int maxIndex = start;
        while (start <= end) {
            if (nums[maxIndex] < nums[start]) {
                maxIndex = start;
            }
            start++;
        }
        return maxIndex;
    }
}
／／ 牛逼的O（n）算法， 用stack 来做的
For an DFS / Recursive Solution, the time complexity will be like a partition sort, worst is O(n^2) and average (nlogn)

However when you think about for each node (or number) , where to find its parent ?
It will be :
1) the right child of the first number that is bigger than it in its left side
2) the left child of the first number that is bigger than it in its right side

whichever is smaller will be its parent.

e.g. 3, 2, 1, 6, 0, 5

for number 2, in its left side, the first number that is bigger than it is 3 and in its right side, the first number that is bigger than it is 6.

3 is smaller than 6, so 2 will be the right child of 3.

As for how to find those two number , please look at the following codes for how the stack is operated.

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        
              
        for (int i = 0; i < nums.length; i++) {
            TreeNode current = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > stack.peek().val) {
                TreeNode node = stack.pop();
                if (stack.isEmpty() || nums[i] < stack.peek().val) current.left = node;
                else stack.peek().right = node;
            }
            stack.push(current);
        }
        while (stack.size() > 1) {
            TreeNode top = stack.pop();
            stack.peek().right = top;
        }
        
        return stack.peek();
    }
}
