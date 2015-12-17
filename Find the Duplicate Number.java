/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

*/
public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 1;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= middle) {
                    sum ++;
                }
            }
            if (sum > middle) {
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }
        }
        return start;
    }
}
