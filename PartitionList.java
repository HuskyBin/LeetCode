public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        ListNode pNode = head;
        while (pNode != null && pNode.val < x) {
            preNode = pNode;
            pNode = pNode.next;
        }
        if (pNode == null) {
            return head;
        }
        ListNode preSmallNode = pNode;
        ListNode smallNode = pNode.next;
        while (smallNode != null) {
            if (smallNode.val < x) {
                ListNode nextSamllNode = smallNode.next;
                preSmallNode.next = nextSamllNode;
                smallNode.next = pNode;
                preNode.next = smallNode;
                preNode = smallNode;
                smallNode = nextSamllNode;
            }
            else {
                preSmallNode = smallNode;
                smallNode = smallNode.next;
            }
        }
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
