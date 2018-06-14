/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
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
/*
Better
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
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Node node = dfsCore(root);
        return Math.max(node.first, node.second);
    }
    
    private Node dfsCore(TreeNode pNode) {
        if (pNode == null) {
            return new Node(0, 0);
        }
        Node left = dfsCore(pNode.left);
        Node right = dfsCore(pNode.right);
        int maxFirst = left.second + right.second + pNode.val;
        int maxSecond = Math.max(right.first, right.second) + Math.max(left.first, left.second);
        return new Node(maxFirst, maxSecond);
    }
}

class Node {
    int first;
    int second;
    
    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }
}


public class Solution {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> path = new ArrayList<>();
        return Math.max(robCore(root, true, map, path),robCore(root, false, map, path));
    }
    
    private int robCore(TreeNode pNode, boolean isRob, Map<String, Integer> map, List<TreeNode> path) {
        if (pNode == null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for(TreeNode ele : path) {
            sb.append(Integer.toString(ele.val));
            sb.append(",");
        }
        sb.append(Integer.toString(pNode.val));
        sb.append(",");
        sb.append(Boolean.toString(isRob));
        String key = sb.toString();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int sum = 0;
        path.add(pNode);
        if (isRob == true) {
            sum += pNode.val;
            sum += robCore(pNode.left, false, map, path) + robCore(pNode.right, false, map, path);
        }
        else {
            int max = Math.max((robCore(pNode.left, true, map, path) + robCore(pNode.right, true, map, path)), (robCore(pNode.left, false, map, path) + robCore(pNode.right, false, map, path)));
            max = Math.max(max,  (robCore(pNode.left, true, map, path) + robCore(pNode.right, false, map, path)));
            max = Math.max(max, (robCore(pNode.left, false, map, path) + robCore(pNode.right, true, map, path)));
            sum += max;
        }
        map.put(key, sum);
        path.remove(path.size() - 1);
        
        return sum;
    }
}
