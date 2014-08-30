/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode pNode = dummyNode;
        Queue<ListNode> queue = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
            public int compare(ListNode left, ListNode right) {
                if (left == null) {
                    return 1;
                }
                if (right == null) {
                    return -1;
                }
                return Integer.compare(left.val, right.val);
            }
        });
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                queue.add(lists.get(i));
            }
        }
        while (!queue.isEmpty()) {
            ListNode topNode = queue.poll();
            pNode.next = topNode;
            pNode = topNode;
            if (topNode.next != null) {
                queue.add(topNode.next);
            }
        }
        ListNode head = dummyNode.next;
        dummyNode.next = null;
        return head;
    }
}
