public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        ListNode pNode = head;
        int tempM = m;
        while (tempM > 1) {
            preNode = pNode;
            pNode = pNode.next;
            tempM--;
        }
        ListNode preReverseNode = null;
        ListNode reverseNewEnd = pNode;
        int tempStep = n - m;
        while (tempStep >= 0) {
            ListNode nextNode = pNode.next;
            pNode.next = preReverseNode;
            preReverseNode = pNode;
            pNode = nextNode;
            tempStep--;
        }
        preNode.next = preReverseNode;
        reverseNewEnd.next = pNode;
        head = dummyNode.next;
        return head;
    }
}
