public class RemoveDupicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preNode = head;
        ListNode pNode = head.next;

        while (pNode != null) {
            if (pNode.val == preNode.val) {
                ListNode nextNode = pNode.next;
                preNode.next = nextNode;
                pNode.next = null;
                pNode = nextNode;
            }
            else {
                preNode = pNode;
                pNode = pNode.next;
            }
        }
        return head;
    }
}


//Update 06/09/2014
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pNode = head;
        while (pNode != null) {
            ListNode nextNode = pNode.next;
            while (nextNode != null && nextNode.val == pNode.val) {
                nextNode = nextNode.next;
            }
            pNode.next = nextNode;
            pNode = pNode.next;
        }
        return head;
    }
}
