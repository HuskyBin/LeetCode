/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...

Credits:
Special thanks to @DjangoUnchained for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode tmpEvenHead = head.next;
        ListNode preOdd = null;
        
        while ((oddHead != null && oddHead.next != null) || (evenHead != null && evenHead.next != null)) {
            if (oddHead != null && oddHead.next != null) {
                ListNode nextOdd = oddHead.next.next;
                oddHead.next = nextOdd;
                preOdd = oddHead;
                oddHead = nextOdd;
            }
            if (evenHead != null && evenHead.next != null) {
                ListNode nextEven = evenHead.next.next;
                evenHead.next = nextEven;
                evenHead = nextEven;
            }
        }
        ListNode oddEnd = null;
        ListNode evenEnd = null;
        if (oddHead != null) {
            oddEnd = oddHead;
        }
        else {
            oddEnd = preOdd;
        }
        
        oddEnd.next = tmpEvenHead;
        return head;
    }
}
