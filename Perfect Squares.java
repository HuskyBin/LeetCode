/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp= new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        int square = 2;
        for (int i = 2; i <= n; i++) {
            if (i == square * square) {
                square++;
                dp[i] = 1;
            }
            else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= i / 2; j++) {
                    min = Math.min(min, dp[j] + dp[i - j]);
                }
                dp[i] = min;
            }
        }
        return dp[n];
    }
}
