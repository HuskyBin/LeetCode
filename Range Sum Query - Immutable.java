/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/
public class NumArray {

      private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        SumArray(nums);
    }

    private void SumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = sum + nums[i];
            sum = nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (j < i || j < 0 || i < 0 || i >= nums.length || i >= nums.length) {
            return -1;
        }
        if (i == 0) {
            return nums[j];
        }
        return nums[j] - nums[i - 1];
    }
}
