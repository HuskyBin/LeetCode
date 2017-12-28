/*
Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
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
    private Map<Integer, EdgeValue> map = new HashMap<>();
    public int findClosestLeaf(TreeNode root, int k) {
        firstProcess(root);
        secondProcess(root);
        return map.get(k).value;
    }
    
    private void secondProcess(TreeNode pNode) {
        if (pNode == null) {
            return;
        }
        if (pNode.left == null && pNode.right == null) {
            return;
        }
        if (pNode.left != null) {
            EdgeValue leftValue = map.get(pNode.left.val);
            if (leftValue.count > map.get(pNode.val).count + 1) {
                map.put(pNode.left.val, new EdgeValue(map.get(pNode.val).count + 1, map.get(pNode.val).value));
            }
            secondProcess(pNode.left);
        }
        if (pNode.right != null) {
            EdgeValue rightValue = map.get(pNode.right.val);
            if (rightValue.count > map.get(pNode.val).count + 1) {
                map.put(pNode.right.val, new EdgeValue(map.get(pNode.val).count + 1, map.get(pNode.val).value));
            }
            secondProcess(pNode.right);
        }
    }
    
    private EdgeValue firstProcess(TreeNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.left == null && pNode.right == null) {
            EdgeValue newValue = new EdgeValue(1, pNode.val);
            map.put(pNode.val, newValue);
            return newValue;
        }
        EdgeValue left = new EdgeValue(Integer.MAX_VALUE, -1);
        EdgeValue right = new EdgeValue(Integer.MAX_VALUE, -1);
        if (pNode.left != null) {
            left = firstProcess(pNode.left);
        }
        if (pNode.right != null) {
            right = firstProcess(pNode.right);
        }
        int count = (left.count < right.count) ? left.count + 1 : right.count + 1;
        int value = (left.count < right.count) ? left.value : right.value;
        EdgeValue valCount = new EdgeValue(count, value);
        map.put(pNode.val, valCount);
        return valCount;
    }
    
    private class EdgeValue {
        public int count;
        public int value;
        public EdgeValue(int count, int value) {
            this.count = count;
            this.value = value;
        }
    }
}
