/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slowNode = dummyNode;
        ListNode fastNode = head;
        int number = n;
        while (number > 0 && fastNode != null) {
            fastNode = fastNode.next;
            number--;
        }
        while (fastNode != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        ListNode nextNode = slowNode.next.next;
        slowNode.next = nextNode;
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
