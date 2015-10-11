/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/
public class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null) {
           return -1; 
        }
        
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i && nums[i] != nums.length) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                result = i;
                break;
            }
        }
        return result;
    }
}
