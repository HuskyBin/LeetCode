/*
Sort a linked list in O(n log n) time using constant space complexity.
*/

// Need to do Quick Sort
// Merge Sort
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middleNode = findMiddleNode(head);
        ListNode nextStartNode = middleNode.next;
        middleNode.next = null;
        ListNode oneHead = sortList(head);
        ListNode twoHead = sortList(nextStartNode);
        ListNode newHead = mergeList(oneHead, twoHead);
        return newHead;
    }
    
    private ListNode mergeList(ListNode oneNode, ListNode twoNode) {
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        ListNode pNode = dummyNode;
        while (oneNode != null || twoNode != null) {
            int firstVal = (oneNode == null) ? Integer.MAX_VALUE : oneNode.val;
            int secondVal = (twoNode == null) ? Integer.MAX_VALUE : twoNode.val;
            if (firstVal <= secondVal && oneNode != null) {
                pNode.next = oneNode;
                oneNode = oneNode.next;
            }
            else {
                pNode.next = twoNode;
                twoNode = twoNode.next;
            }
            pNode = pNode.next;
        }
        ListNode head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
    
    private ListNode findMiddleNode(ListNode pNode) {
        ListNode slowNode = pNode;
        ListNode fastNode = pNode;
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
        return slowNode;
    }
}
