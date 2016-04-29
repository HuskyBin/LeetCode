/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/
class SegmentNode {
    public SegmentNode left;
    public SegmentNode right;
    public int sum;
    public int start;
    public int end;
    
    public SegmentNode(int sum, int start, int end) {
        this.start = start;
        this.end = end;
        this.sum = sum;
    }
}

public class NumArray {

    public SegmentNode root;
    
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        root = buildSegmentTree(nums, 0, nums.length - 1);
    }
    
    private SegmentNode buildSegmentTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            SegmentNode newNode = new SegmentNode(nums[start], start, end);
            return newNode;
        }
        int leftEnd = start + (end - start) / 2;
        int rightStart = leftEnd + 1;
        SegmentNode leftChild = buildSegmentTree(nums, start, leftEnd);
        SegmentNode rightChild = buildSegmentTree(nums, rightStart, end);
        int sum = 0;
        sum += (leftChild == null) ? 0 : leftChild.sum;
        sum += (rightChild == null) ? 0 : rightChild.sum;
        SegmentNode newNode = new SegmentNode(sum, start, end);
        newNode.left = leftChild;
        newNode.right = rightChild;
        return newNode;
    }

    void update(int i, int val) {
        updateSegmentNode(root, i, val);
    }
    
    private void updateSegmentNode(SegmentNode pNode, int index, int value) {
        if (pNode == null) {
            return ;
        }
        if (pNode.start == index && pNode.end == index) {
            pNode.sum = value;
            return;
        }
        int middle = (pNode.start + (pNode.end - pNode.start) / 2);
        if (index <= middle) {
            updateSegmentNode(pNode.left, index, value);
            pNode.sum = pNode.left.sum + pNode.right.sum;
            return;
        }
        else {
            updateSegmentNode(pNode.right, index, value);
            pNode.sum = pNode.right.sum + pNode.left.sum;
            return;
        }
        
    }

    public int sumRange(int i, int j) {
        return querySegmentTree(root, i, j);
    }
    
    private int querySegmentTree(SegmentNode pNode, int start, int end) {
        if (pNode == null) {
            return 0;
        }
        if (pNode.start == start && pNode.end == end) {
            return pNode.sum;
        }
        int middle = (pNode.start + (pNode.end - pNode.start) / 2);
        if (end <= middle) {
            return querySegmentTree(pNode.left, start, end);
        }
        else if (start > middle) {
            return querySegmentTree(pNode.right, start, end);
        }
        else {
            return querySegmentTree(pNode.left, start, middle) + querySegmentTree(pNode.right, middle + 1, end);
        }
    }
}
