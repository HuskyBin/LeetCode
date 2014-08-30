/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode lastGroupNode = dummyNode;
        ListNode secondNode = head;
        ListNode firstNode = head.next;
        
        while (firstNode != null) {
            int number = k;
            while (number > 1 && firstNode != null) {
                ListNode nextNode = firstNode.next;
                firstNode.next = secondNode;
                secondNode = firstNode;
                firstNode = nextNode;
                number--;
            }
            if (number == 1) {
                ListNode newGroupLastNode = lastGroupNode.next;
                lastGroupNode.next = secondNode;
                newGroupLastNode.next = firstNode;
                lastGroupNode = newGroupLastNode;
                secondNode = firstNode;
                if (firstNode != null) {
                    firstNode = firstNode.next;
                }
            }
            else {
                while (number < k) {
                    ListNode nextNode = secondNode.next;
                    secondNode.next =firstNode;
                    firstNode = secondNode;
                    secondNode = nextNode;
                    number++;
                }
                break;
            }
        }
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
