/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
                ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pNode = dummyNode;
        while (pNode.next != null) {
            if (pNode.next.val == val) {
                pNode.next = pNode.next.next;
            }
            else {
                pNode = pNode.next;
            }
        }    
        ListNode newHead = dummyNode.next;
        dummyNode.next = null;
        return newHead;
    }
}
