/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/
public class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) {
            return;
        }
        k = k % nums.length;
        rotateCore(nums, 0, nums.length - k - 1);
        rotateCore(nums, nums.length - k, nums.length - 1);
        rotateCore(nums, 0, nums.length - 1);
    }
    
    private void rotateCore(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
