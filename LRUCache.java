/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/


// Need to add Two DummyNode (head, tail) into the Double list at the Beginning. In this way we could solve corner cases easily.

public class LRUCache {
    
    private Map<Integer, ListNode> nodeMap = new HashMap<>();
    private int currentSize = 0;
    private int capacity = 0;
    private ListNode start = null;
    private ListNode end = null;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;    
    }
    
    public int get(int key) {
        if (this.nodeMap.containsKey(key)) {
            ListNode keyNode = this.nodeMap.get(key);
            int result = keyNode.val;
            moveNodeToFront(keyNode);
            return result;
        }
        else {
            return -1;
        }
    }
    
    private void moveNodeToFront(ListNode pNode) {
        if (pNode.pre != null) {
            if (pNode == this.end) {
                this.end = pNode.pre;
            }
            pNode.pre.next = pNode.next;
            if (pNode.next != null) {
                pNode.next.pre = pNode.pre;
            }
            pNode.next = this.start;
            this.start.pre = pNode;
            pNode.pre = null;
            this.start = pNode;
        }
    }
    
    private void addNewNodeToFront(ListNode pNode, int key) {
        if (this.currentSize == 0) {
            this.start = pNode;
            this.end = pNode;
        }
        else {
            pNode.next = this.start;
            this.start.pre = pNode;
            this.start = pNode;
        }
        this.nodeMap.put(key, pNode);
        this.currentSize++;
    }
    
    private void deleteLastNode() {
        this.nodeMap.remove(this.end.key);
        if (this.end == this.start) {
            this.end = null;
            this.start = null; 
        }
        else {
            this.end = this.end.pre;
            this.end.next = null;
        }
        this.currentSize--;
    }
    
    public void set(int key, int value) {
        if (this.capacity <= 0) {
            return;
        }
        if (this.nodeMap.containsKey(key)) {
            ListNode keyNode = this.nodeMap.get(key);
            keyNode.val = value;
            moveNodeToFront(keyNode);
        }
        else {
            ListNode newNode = new ListNode(value, key);
            if (this.currentSize < this.capacity) {
                addNewNodeToFront(newNode, key);
            }
            else {
                deleteLastNode();
                addNewNodeToFront(newNode, key);
            }
        }
    }
}

class ListNode {
    public int val;
    public ListNode pre;
    public ListNode next;
    public int key;
    
    public ListNode(int x, int key) {
        this.val = x;
        this.pre = null;
        this.next = null;
        this.key = key;
    }
}
