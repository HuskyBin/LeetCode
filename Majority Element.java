/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        Integer result = 0;
        int count = 0;
        for (int num : nums) {
            if (result != null && result == num) {
                count++;
            }
            else if (count == 0) {
                count = 1;
                result = num;
            }
            else {
                count--;
            }
        }
        return result;
    }
}
