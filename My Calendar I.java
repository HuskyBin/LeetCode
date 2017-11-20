/*
Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
*/
class MyCalendar {
    TreeNode root;
    public MyCalendar() {
        
    }
    
    public boolean book(int start, int end) {
        if (start >= end) {
            return false;
        }
        if (root == null) {
            root = new TreeNode(start, end - 1);
            return true;
        }
        if (buildTree(root, start, end - 1) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public TreeNode buildTree(TreeNode pNode, int start, int end) {
        if (pNode == null) {
            return new TreeNode(start, end);
        }
        if ((start >= pNode.start && start <= pNode.end) || (end >= pNode.start && end <= pNode.end)) {
            return null;
        }
        if (start > pNode.end) {
            TreeNode rightChild = buildTree(pNode.right, start, end);
            if (rightChild == null) {
                return null;
            }
            pNode.right = rightChild;
        }
        else if (end < pNode.start) {
            TreeNode leftChild = buildTree(pNode.left, start, end);
            if (leftChild == null) {
                return null;
            }
            pNode.left = leftChild;
        }
        else {
            return null;
        }
        return pNode;
    }
    
    class TreeNode {
        public int start;
        public int end;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
