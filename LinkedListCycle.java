/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
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
                return true;
            }
        }
        return false;
    }
}
