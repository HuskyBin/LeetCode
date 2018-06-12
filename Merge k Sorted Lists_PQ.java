/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummyNode = new ListNode(1);
        ListNode pNode = dummyNode;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            pNode.next = curNode;
            pNode = curNode;
            if (curNode.next != null) {
                queue.offer(curNode.next);
            }
        }
        ListNode head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
