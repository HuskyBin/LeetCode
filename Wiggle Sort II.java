/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/
public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = nums[i];
        }
        int start = (nums.length - 1) / 2;
        int end = nums.length - 1;
        Arrays.sort(tmp);
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = tmp[start--];
            }
            else {
                nums[i] = tmp[end--];
            }
        }
    }
}
