/*
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
*/
public class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public int length;
    public ListNode headNode;
    public Solution(ListNode head) {
        if (head == null) {
            return;
        }
        headNode = head;
        ListNode pNode = head;
        while (pNode != null) {
            length++;
            pNode = pNode.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random random = new Random();
        int nextRandom = random.nextInt(length);
        ListNode curNode = headNode;
        while (nextRandom > 0) {
            curNode = curNode.next;
            nextRandom--;
        }
        return curNode.val;
    }
}
