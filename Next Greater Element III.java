/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
Example 2:
Input: 21
Output: -1
*/
class Solution {
    public int nextGreaterElement(int n) {
        
        char[] nums = String.valueOf(n).toCharArray();
        if (nums.length == 1) {
            return -1;
        }
        int firstSmallIndex = nums.length - 2;
        int previousIndex = nums.length - 1;
        while (firstSmallIndex >= 0) {
            if (nums[firstSmallIndex] < nums[previousIndex]) {
                break;
            }
            previousIndex = firstSmallIndex;
            firstSmallIndex--;
        }
        if (firstSmallIndex < 0) {
            return -1;
        }
        int firstLargerIndex = nums.length - 1;
        while (firstLargerIndex > firstSmallIndex) {
            if (nums[firstLargerIndex] > nums[firstSmallIndex]) {
                break;
            }
            firstLargerIndex--;
        }
        char temp = nums[firstLargerIndex];
        nums[firstLargerIndex] = nums[firstSmallIndex];
        nums[firstSmallIndex] = temp;
        firstSmallIndex++;
        reverseNum(nums, firstSmallIndex);
        long val = Long.parseLong(String.valueOf(nums));
        return val <= Integer.MAX_VALUE ? (int)val : -1;
    }
    
    
    private void reverseNum(char[] nums, int index) {
        int start = index;
        int end = nums.length - 1;
        while (start < end) {
            char temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
