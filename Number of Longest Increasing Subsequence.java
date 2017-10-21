/*
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.


*/
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[] lens = new int[nums.length];
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lens[i] = 1;
            count[i] = 1;
        }
        int maxLength = 0;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lens[i] == lens[j] + 1) {
                        count[i] += count[j];
                    }
                    else if (lens[i] < lens[j] + 1) {
                        lens[i] = lens[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLength == lens[i]) {
                maxCount += count[i];
            }
            if (maxLength < lens[i]) {
                maxLength = lens[i];
                maxCount = count[i];
            }
        }
        return maxCount;
    }
}
