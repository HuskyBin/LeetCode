/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
*/
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        int len = n - m;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode fastNode = head;
        ListNode slowNode = dummyNode;

        while (m > 1) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
            m--;
        }
        ListNode firstPartEnd = slowNode;
        ListNode reverseNewEnd = fastNode;
        ListNode preNode = null;
        ListNode pNode = fastNode;

        while (len >= 0) {
            ListNode nextNode = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = nextNode;
            len--;
        }
        reverseNewEnd.next = pNode;
        firstPartEnd.next = preNode;
        ListNode newHead = dummyNode.next;
        dummyNode.next = null;
        return newHead;
    }
}
