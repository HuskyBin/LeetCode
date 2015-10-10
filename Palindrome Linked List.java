/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
*/
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode middle = partitionList(head);
        ListNode startNode = reverseList(middle);
        
        while (startNode != null && head != null) {
            if (startNode.val != head.val) {
                return false;
            }
            startNode = startNode.next;
            head = head.next;
        }
        return true;
    }
    
    private ListNode reverseList(ListNode pNode) {
        ListNode preNode = null;
        while(pNode != null) {
            ListNode nextNode = pNode.next;
            pNode.next = preNode;
            preNode = pNode;
            pNode = nextNode;
        }
        return preNode;
    }
    
    private ListNode partitionList(ListNode pNode) {
        ListNode fastNode = pNode;
        ListNode slowNode = pNode;
        while(fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        ListNode startNode = slowNode.next;
        slowNode.next = null;
        return startNode;
    }
}


// Best solution

class Result {
    ListNode node;
    boolean result;
    
    public Result(ListNode node, boolean result) {
        this.node = node;
        this.result = result;
    }
}
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        int length = 0;
        ListNode pNode = head;
        while (pNode != null) {
            pNode = pNode.next;
            length += 1;
        }
        Result result = isPlainDromeCore(head, length);
        return result.result;
    }
    
    private Result isPlainDromeCore(ListNode head, int length) {
        if (head == null || length == 0) {
            return new Result(null, true);
        }
        if (length == 1){
            return new Result(head.next, true);
        }
        if (length == 2){
            return new Result(head.next.next, (head.val == head.next.val));
        }
        
        Result res = isPlainDromeCore(head.next, length - 2);
        if (!res.result) {
            return res;
        }
        else {
            res.result = head.val == res.node.val;
            res.node = res.node.next;
            return res;
        }
    }
    
}
