/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/
//Memory Search Dp
public class Solution {
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        int result = numTreesCore(n, n, dp);
        return result;
    }
    
    private int numTreesCore(int index, int num, int[] dp) {
        if (index == 0) {
            return 1;
        }
        if (index == 1) {
            return 1;
        }
        if (dp[index] > 0) {
            return dp[index];
        }
        int subSum = 0;
        for (int i = 1; i <= index; i++) {
            subSum += numTreesCore(i - 1, num, dp) * numTreesCore(index - i, num, dp);
        }
        dp[index] = subSum;
        return subSum;
    }
}
