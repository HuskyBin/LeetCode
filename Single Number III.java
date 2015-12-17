/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        int pivot = xor & (~(xor - 1));
        int partitionOne = 0;
        int partitionTwo = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & pivot) != 0) {
                partitionOne ^= nums[i];
            }
            else {
                partitionTwo ^= nums[i];
            }
        }
        int[] result = new int[2];
        result[0] = partitionOne;
        result[1] = partitionTwo;
        return result;
    }
}
