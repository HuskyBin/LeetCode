/*
Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
*/
public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        // Write your code here
        if (nums == null) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i  -1);
                }
            }
            else {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }
    
    private void swap(int[] nums, int aIndex, int bIndex) {
        int temp = nums[aIndex];
        nums[aIndex] = nums[bIndex];
        nums[bIndex] = temp;
    }
}
