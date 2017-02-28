/*
Total Accepted: 5333
Total Submissions: 10249
Difficulty: Medium
Contributors: Cyber233
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
    class SumObj {
        public int sum;
        public int frequency;
        
        public SumObj(int sum, int frequency) {
            this.sum = sum;
            this.frequency = frequency;
        }
    }
    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        countSubTreeSum(root, map);
        PriorityQueue<SumObj> queue = new PriorityQueue<>(1, new Comparator<SumObj>() {
            public int compare(SumObj a, SumObj b) {
                return b.frequency - a.frequency;
            }
        });
        for (int sum : map.keySet()) {
            SumObj newObj = new SumObj(sum, map.get(sum));
            queue.add(newObj);
        }
        int topFrequency = queue.peek().frequency;
        while (!queue.isEmpty() && topFrequency == queue.peek().frequency) {
            resultList.add(queue.poll().sum);
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    private int countSubTreeSum(TreeNode pNode, Map<Integer, Integer> map) {
        if (pNode == null) {
            return 0;
        }
        int leftSum = countSubTreeSum(pNode.left, map);
        int rightSum = countSubTreeSum(pNode.right, map);
        int totalSum = leftSum + rightSum + pNode.val;
        if (!map.containsKey(totalSum)) {
            map.put(totalSum, 0);
        }
        map.put(totalSum, map.get(totalSum) + 1);
        return totalSum;
    }
}
