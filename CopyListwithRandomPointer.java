/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        Map<RandomListNode, RandomListNode> cloneNodeMap = new HashMap<>();
        RandomListNode pNode = head;
        while (pNode != null) {
            RandomListNode newCloneNode = new RandomListNode(pNode.label);
            cloneNodeMap.put(pNode, newCloneNode);
            pNode = pNode.next;
        }
        pNode = head;
        while (pNode != null) {
            RandomListNode cloneNode = cloneNodeMap.get(pNode);
            RandomListNode nextNode = pNode.next;
            if (nextNode == null) {
                cloneNode.next = null;
            }
            else {
                cloneNode.next = cloneNodeMap.get(nextNode);
            }
            RandomListNode randomNode = pNode.random;
            if (randomNode == null) {
                cloneNode.random = null;
            }
            else {
                cloneNode.random = cloneNodeMap.get(randomNode);
            }
            pNode = nextNode;
        }
        return cloneNodeMap.get(head);
    }
}
