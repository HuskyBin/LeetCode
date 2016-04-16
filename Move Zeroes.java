/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int index = 0;
        int zeroNums = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (index != i) {
                    nums[index] = nums[i];    
                }
                index++;
            } 
            else if (nums[i] == 0) {
                zeroNums++;
            }
        }
        for (int i = 0; i < zeroNums; i++) {
            nums[index++] = 0;
        }
    }
}

// Second Solution
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        
        for (int zeroIndex = 0, nonZeroIndex = 0; zeroIndex < nums.length && nonZeroIndex < nums.length; ) {
            if (nums[zeroIndex] != 0) {
                zeroIndex++;
                if (zeroIndex > nonZeroIndex) {
                    nonZeroIndex = zeroIndex;    
                }
                continue;
            }
            if (nums[nonZeroIndex] == 0) {
                nonZeroIndex++;
                continue;
            }
            int tmp = nums[zeroIndex];
            nums[zeroIndex] = nums[nonZeroIndex];
            nums[nonZeroIndex] = tmp;
        }
    }
}
