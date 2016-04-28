/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int min = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    min = Math.max(min, dp[j]);
                }
            }
            dp[i] = min + 1;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
