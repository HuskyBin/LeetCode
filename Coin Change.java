/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

dp[x + c] = min(dp[x + c], dp[x] + 1)
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
        }
        for (int i = 0; i <= amount; i++) {
            if (dp[i] < 0) {
                continue;
            }
            for (int j = 0; j < coins.length; j++) {
                if (i + coins[j] > amount) {
                    continue;
                }
                else if (dp[i + coins[j]] > dp[i] + 1 || dp[i + coins[j]] < 0) {
                    dp[i + coins[j]] = dp[i] + 1;
                }
            }
        }
        return dp[amount];
    }
}
