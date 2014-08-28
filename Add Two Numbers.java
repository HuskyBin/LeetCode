/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode pNode = dummyNode;
        ListNode firstListNode = l1;
        ListNode secondListNode = l2;
        int carry = 0;
        while (firstListNode != null || secondListNode != null) {
            int firstNum = firstListNode == null ? 0 : firstListNode.val;
            int secondNum = secondListNode == null ? 0 : secondListNode.val;
            int sum = firstNum + secondNum + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            pNode.next = newNode;
            pNode = newNode;
            firstListNode = firstListNode == null ? null : firstListNode.next;
            secondListNode = secondListNode == null ? null : secondListNode.next;
        }
        if (carry >= 1) {
            ListNode newNode = new ListNode(carry);
            pNode.next = newNode;
        }
        ListNode head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
