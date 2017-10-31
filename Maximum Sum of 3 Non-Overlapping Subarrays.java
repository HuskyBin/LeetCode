/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
*/
题解
https://leetcode.com/articles/maximum-sum-of-3-non-overlapping-intervals/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] prefixSum = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                prefixSum[i - k + 1] = sum;
            }
        }
        
        int[] left = new int[prefixSum.length];
        int best = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] > prefixSum[best]) {
                best = i;
            }
            left[i] = best;
        }
        int[] right = new int[prefixSum.length];
        best = prefixSum.length - 1;
        for (int i = prefixSum.length - 1; i >= 0; i--) {
            if (prefixSum[i] > prefixSum[best]) {
                best = i;
            }
            right[i] = best;
        }
        
        int[] res = {0, k, 2 * k};
        for (int j = k; j < prefixSum.length - k; j++) {
            int i = left[j - k];
            int x = right[j + k];
            if (prefixSum[i] + prefixSum[j] + prefixSum[x] > prefixSum[res[0]] + prefixSum[res[1]] + prefixSum[res[2]]) {
                res[0] = i;
                res[1] = j;
                res[2] = x;
            }
        }
        return res;
    }
}
