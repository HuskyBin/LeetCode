/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

public class Solution {
    public int rob(int[] num) {
        
        if (num == null || num.length == 0) {
            return 0;
        }
        int[] dp = new int[num.length + 1];
        dp[0] = 0;
        dp[1] = num[0];
        for (int i = 2; i <= num.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + num[i - 1]);
        }
        return dp[num.length];
           
    }
}
