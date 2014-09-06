public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
    
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode preNode = dummyNode;
        ListNode pNode = head;
        
        while (pNode != null) {
            if (pNode.next != null && (pNode.val == pNode.next.val)) {
                while (pNode.next != null && pNode.val == pNode.next.val) {
                    pNode = pNode.next;
                }
                pNode = pNode.next;
                preNode.next = pNode;
            }
            else {
                preNode = pNode;
                pNode = pNode.next;
            }
        }
    
        head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
