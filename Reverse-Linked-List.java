
//Reverse a singly linked list.


// Iteratively
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }       
        ListNode preNode = null;
        ListNode pNode = head;
        while (pNode != null) {
            ListNode nextNode = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = nextNode;
        }
        return preNode;
    }
}


//recursion
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }       
        return reverseListCore(head, null);
    }

    private ListNode reverseListCore(ListNode pNode, ListNode preNode) {
        if (pNode == null) {
            return preNode;
        }
        ListNode nextNode = pNode.next;
        pNode.next = preNode;
        return reverseListCore(nextNode, pNode);
    }
}
