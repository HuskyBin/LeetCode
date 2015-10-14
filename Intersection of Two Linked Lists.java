/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
                if (headA == null || headB == null) {
            return null;
        }
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);

        ListNode lessList = (lengthA < lengthB) ? headA : headB;
        ListNode longerList = (lengthA < lengthB) ? headB : headA;

        int diff = Math.abs(lengthA - lengthB);
        while (diff > 0) {
            longerList = longerList.next;
            diff--;
        }

        while (lessList != null && longerList != null) {
            if (lessList == longerList) {
                return lessList;
            }
            lessList = lessList.next;
            longerList = longerList.next;
        }
        return null;
    }
    
    private int getLength(ListNode pNode) {
        int length = 0;
        while (pNode != null) {
            pNode = pNode.next;
            length++;
        }
        return length;
    }
}
