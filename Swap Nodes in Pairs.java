/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.


*/
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode firstNode = head;
        ListNode secondNode = dummyNode;
        while (firstNode != null && firstNode.next != null) {
            ListNode nextNode = firstNode.next;
            firstNode.next = nextNode.next;
            nextNode.next = firstNode;
            secondNode.next = nextNode;
            secondNode = firstNode;
            firstNode = firstNode.next;
        }
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
