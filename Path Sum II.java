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



// No-recursion Version
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<List<Integer>> path = new Stack<>();
        stack.push(root);
        List<Integer> listRoot = new ArrayList<>();
        listRoot.add(root.val);
        path.push(listRoot);
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(sum - root.val);
        while (stack.size() > 0) {
            TreeNode pNode = stack.pop();
            List<Integer> pathList = path.pop();
            int curSum = sumStack.pop();
            if (pNode.left == null && pNode.right == null && curSum == 0) {
                resultList.add(new ArrayList<Integer>(pathList));
            }
            if (pNode.right != null) {
                List<Integer> rightPath = new ArrayList<>(pathList);
                rightPath.add(pNode.right.val);
                path.push(rightPath);
                stack.push(pNode.right);
                sumStack.push(curSum - pNode.right.val);
            }
            if (pNode.left != null) {
                List<Integer> leftPath = new ArrayList<>(pathList);
                leftPath.add(pNode.left.val);
                path.push(leftPath);
                stack.push(pNode.left);
                sumStack.push(curSum - pNode.left.val);
            }
        }
        return resultList;
    }
}
