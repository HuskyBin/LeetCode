// 用双dummyNode来把不同的点穿起来
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);
        
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        ListNode pNode = head;
        
        while (pNode != null) {
            if (pNode.val < x) {
                left.next = pNode;
                left = pNode;
            }
            else {
                right.next = pNode;
                right = pNode;
            }
            pNode = pNode.next;
        }
        right.next = null;
        left.next = rightDummy.next;
        head = leftDummy.next;
        return head;
    }
}
