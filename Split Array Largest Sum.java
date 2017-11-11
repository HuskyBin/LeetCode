/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

*/
class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums == null || m < 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][nums.length + 1];
        int[] sum = new int[nums.length + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= nums.length; j++) {
                for (int k = i - 1; k < j; k++) {
                    int val = Math.max(dp[i - 1][k], sum[j] - sum[k]);
                    dp[i][j] = Math.min(dp[i][j], val);           
                }
            }
        }
        return dp[m][nums.length];
    }
}
