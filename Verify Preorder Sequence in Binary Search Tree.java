/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        return verifyCore(preorder, 1, preorder.length - 1, preorder[0]);
    }
    
    private boolean verifyCore(int[] preorder, int start, int end, int root) {
        if (start > end) {
            return true;
        }
        int index = start;
        int middleIndex = start - 1;
        while (index <= end && preorder[index] < root) {
            middleIndex = index;
            index++;
        }
        while (index <= end) {
            if (preorder[index] < root) {
                return false;
            }
            index++;
        }
        boolean result = true;
        if (middleIndex > start) {
            result &= verifyCore(preorder, start + 1, middleIndex, preorder[start]);
        }
        if (result == false) {
            return false;
        }
        if (end > (middleIndex + 2)) {
            result &= verifyCore(preorder, middleIndex + 2, end, preorder[middleIndex + 1]);
        }
        return result;
    }
}
