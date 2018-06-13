/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/
// beeter

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<Integer, RandomListNode> copyMap = new HashMap<>();
        RandomListNode pNode = head;
        RandomListNode preNode = null;
        while (pNode != null) {
            RandomListNode copyNode = copyMap.getOrDefault(pNode.label, new RandomListNode(pNode.label));
            copyMap.putIfAbsent(pNode.label, copyNode);
            if (preNode != null) {
                copyMap.get(preNode.label).next = copyNode;
            }
            if (pNode.random != null) {
                RandomListNode copyRandomNode = copyMap.getOrDefault(pNode.random.label, new RandomListNode(pNode.random.label));
                copyMap.putIfAbsent(pNode.random.label, copyRandomNode);
                copyNode.random = copyRandomNode;
            }
            preNode = pNode;
            pNode = pNode.next;
        }
        return copyMap.get(head.label);
    }
}


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

// version Two
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        RandomListNode pNode = head;
        while (pNode != null) {
            RandomListNode newNode = new RandomListNode(pNode.label);
            RandomListNode nextNode = pNode.next;
            newNode.next = nextNode;
            pNode.next = newNode;
            pNode = nextNode;
        }
        pNode = head;
        while (pNode != null) {
            RandomListNode nextRandomNode = pNode.next;
            if (pNode.random != null) {
                nextRandomNode.random = pNode.random.next;
            }
            pNode = nextRandomNode.next;
        }
        
        RandomListNode newDummyNode = new RandomListNode(-1);
        RandomListNode oldDummyNode = new RandomListNode(-1);
        
        RandomListNode newLink = newDummyNode;
        RandomListNode newOld = oldDummyNode;
        pNode = head;
        while (pNode != null) {
            newOld.next = pNode;
            newLink.next = pNode.next;
            newOld = newOld.next;
            newLink = newLink.next;
            pNode = pNode.next.next;
        }
        newOld.next = null;
        oldDummyNode.next = null;
        RandomListNode copyHead = newDummyNode.next;
        newDummyNode.next = null;
        return copyHead;
    }
}
