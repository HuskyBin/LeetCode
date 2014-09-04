/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || n < 0) {
            return head;
        }
        int length = getLength(head);
        n = n % length;
        ListNode fastNode = head;
        ListNode slowNode = head;
        int temp = n;
        while (temp > 0) {
            fastNode = fastNode.next;
            temp--;
        }
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        fastNode.next = head;
        head = slowNode.next;
        slowNode.next = null;
        return head;
    }
    
    private int getLength(ListNode head) {
        int num = 0;
        while (head != null) {
            num++;
            head = head.next;
        }
        return num;
    }
}
