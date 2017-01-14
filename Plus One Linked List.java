/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }
        int carry = plusCore(head);
        if (carry > 0) {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }
    
    private int plusCore(ListNode pNode) {
        if (pNode == null) {
            return 1;
        }
        int carry = plusCore(pNode.next);
        int sum = carry + pNode.val;
        pNode.val = sum % 10;
        carry = sum / 10;
        return carry;
    }
}
