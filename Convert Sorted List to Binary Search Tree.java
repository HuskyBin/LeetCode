/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/
public class Solution {
    public ListNode pNode = null;
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        int length = getListLength(head);
        pNode = head;
        TreeNode rootNode = sortedListToBSTCore(0, length - 1);
        return rootNode;
    }
    
    private int getListLength(ListNode pNode) {
        int length = 0;
        while (pNode != null) {
            length++;
            pNode = pNode.next;
        }
        return length;
    }
    
    private TreeNode sortedListToBSTCore(int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = start + (end - start) / 2;
        TreeNode pLeft = sortedListToBSTCore(start, middle - 1);
        TreeNode newNode = new TreeNode(pNode.val);
        pNode = pNode.next;
        TreeNode pRight = sortedListToBSTCore(middle + 1, end);
        newNode.left = pLeft;
        newNode.right = pRight;
        return newNode;
    }
}
