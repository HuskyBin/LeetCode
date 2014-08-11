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
