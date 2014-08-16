/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

public class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null) {
            fastNode = fastNode.next;
            if (fastNode == null) {
                break;
            }
            fastNode = fastNode.next;
            if (fastNode == null) {
                break;
            }
            slowNode = slowNode.next;
        }

        ListNode endNode = reverseList(slowNode);
        ListNode startNode = head;
        while (startNode.next != endNode && startNode != endNode) {
            ListNode nextEndNode = endNode.next;
            ListNode nextStartNode = startNode.next;
            endNode.next = nextStartNode;
            startNode.next = endNode;
            endNode = nextEndNode;
            startNode = nextStartNode;
        }
        endNode.next = null;
    }
    
    public ListNode reverseList(ListNode slowNode) {
        ListNode preNode = slowNode;
        ListNode pNode = preNode.next;
        while (pNode != null) {
            ListNode nextNode = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = nextNode;
        }
        return preNode;
    }

}
