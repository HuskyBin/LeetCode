/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        
         PriorityQueue<Integer> queue = new PriorityQueue<Integer>(1, (a, b) -> (
             (Math.abs(a - target) == Math.abs(b - target)) ? 0 : ((Math.abs(a - target) < Math.abs(b - target)) ? 1 : -1)
        ));
        
        travelCore(root, queue, k, target);
        while (queue.size() > 0) {
            resultList.add(queue.poll());
        }
        return resultList;
    }
    
    private void travelCore(TreeNode pNode, PriorityQueue<Integer> heap, int k, double target) {
        if (pNode == null) {
            return;
        }
        if (heap.size() < k) {
            heap.add(pNode.val);
        }
        else {
            int topValue = heap.peek();
            if (Math.abs(topValue - target) > Math.abs(pNode.val - target)) {
                heap.poll();
                heap.add(pNode.val);
            }
        }
        travelCore(pNode.left, heap, k, target);
        travelCore(pNode.right, heap, k, target);
    }
}
