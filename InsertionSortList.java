/*
Sort a linked list using insertion sort.
*/
//My own version
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        dummyNode.next = head;
        ListNode curNode = head.next;
        ListNode curPreNode = head;
        while (curNode != null) {
            ListNode preNode = dummyNode;
            ListNode pNode = dummyNode.next;
            ListNode nextNode = curNode.next;
            while (curNode.val >= pNode.val && curNode != pNode) {
                preNode = pNode;
                pNode = pNode.next;
            }
            if (curNode != pNode) {
                curPreNode.next = nextNode;
                curNode.next = pNode;
                preNode.next = curNode;
            }
            else {
                curPreNode = curNode;
            }
            curNode = nextNode;
        }
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}

//Consice Version
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        ListNode curNode = head;
        while (curNode != null) {
            ListNode pNode = dummyNode;
            ListNode nextNode = curNode.next;
            while (pNode.next != null && pNode.next.val < curNode.val) {
                pNode = pNode.next;
            }
            curNode.next = pNode.next;
            pNode.next = curNode;
            curNode = nextNode;
        }
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
