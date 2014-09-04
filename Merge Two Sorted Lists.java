/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
*/
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode pNode = dummyNode;
        while (l1 != null || l2 != null) {
            int num1 = (l1 == null) ? Integer.MAX_VALUE : l1.val;
            int num2 = (l2 == null) ? Integer.MAX_VALUE: l2.val;
            if (num1 <= num2 && l1 != null) {
                pNode.next = l1;
                l1 = l1.next;
                pNode = pNode.next;
            }
            else {
                pNode.next = l2;
                l2 = l2.next;
                pNode = pNode.next;
            }
        }
        ListNode head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
