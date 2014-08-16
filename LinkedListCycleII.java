/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null) {
            fastNode = fastNode.next;
            if (fastNode == null) {
                break;
            }
            fastNode = fastNode.next;
            slowNode = slowNode.next;
            if (slowNode == fastNode) {
                break;
            }
        }
        if (fastNode == null) {
            return null;
        }
        fastNode = head;
        while (fastNode != slowNode) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        } 
        return slowNode;
    }
}
