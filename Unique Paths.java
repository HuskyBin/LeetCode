A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Note: m and n will be at most 100.
// Memory Search
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        int result = uniquePathsCore(m - 1, n - 1, dp);
        return result;
    }
    
    
    private int uniquePathsCore(int m, int n, int[][] dp) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 0 && n == 0) {
            dp[0][0] = 1;
            return 1;
        }
        if (dp[m][n] > 0) {
            return dp[m][n];
        }
        dp[m][n] = uniquePathsCore(m - 1, n, dp) + uniquePathsCore(m, n - 1, dp);
        return dp[m][n];
    }
}


// For Loop Dp
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n<= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
