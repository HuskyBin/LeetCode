/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
*/
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode middle = partitionList(head);
        ListNode startNode = reverseList(middle);
        
        while (startNode != null && head != null) {
            if (startNode.val != head.val) {
                return false;
            }
            startNode = startNode.next;
            head = head.next;
        }
        return true;
    }
    
    private ListNode reverseList(ListNode pNode) {
        ListNode preNode = null;
        while(pNode != null) {
            ListNode nextNode = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = nextNode;
        }
        return preNode;
    }
    
    private ListNode partitionList(ListNode pNode) {
        ListNode fastNode = pNode;
        ListNode slowNode = pNode;
        while(fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        ListNode startNode = slowNode.next;
        slowNode.next = null;
        return startNode;
    }
}
