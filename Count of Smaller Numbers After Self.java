/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
*/
public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        
        SegmentNode root = null;
        
        root = buildTree(min, max);

        for (int i = nums.length - 1; i >= 0; i--) {
            int res = querySegmentNode(root, min, nums[i] - 1);
            modifySegmentNode(root, nums[i], 1);
            resultList.add(0, res);
        }
        return resultList;
    }
    
    private SegmentNode buildTree(int start, int end) {
        if (start == end) {
            SegmentNode newNode = new SegmentNode(start, end, 0);
            return newNode;
        }
        int mid = start + (end - start) / 2;
        SegmentNode newNode = new SegmentNode(start, end, 0);
        SegmentNode left = buildTree(start, mid);
        SegmentNode right = buildTree(mid + 1, end);
        newNode.left = left;
        newNode.right = right;
        return newNode;
    }
    
    private void modifySegmentNode(SegmentNode pNode, int index, int val) {
        if (pNode.start == index && pNode.end == index) {
            pNode.count += val;
            return;
        }
        int mid = pNode.start + (pNode.end - pNode.start) / 2;
        if (index <= mid) {
            modifySegmentNode(pNode.left, index, val);
        }
        else {
            modifySegmentNode(pNode.right, index, val);
        }
        pNode.count = pNode.left.count + pNode.right.count;
    }
    
    private int querySegmentNode(SegmentNode pNode, int start, int end) {
        if (end < start) {
            return 0;
        }
        if (pNode.start == start && pNode.end == end) {
            return pNode.count;
        }
        int mid = pNode.start + (pNode.end - pNode.start) / 2;
        if (end <= mid) {
            return querySegmentNode(pNode.left, start, end);
        }
        else if (start > mid) {
            return querySegmentNode(pNode.right, start, end);
        }
        else {
            return querySegmentNode(pNode.left, start, mid) + querySegmentNode(pNode.right, mid + 1, end);
        }
    }
}

class SegmentNode {
    public int count;
    public int start;
    public int end;
    public SegmentNode left;
    public SegmentNode right;
    
    public SegmentNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }
}
