/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

//  重复用一个变量
public class Solution {
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int same = k;
        int diff = k * (k - 1);
        
        for (int i = 2; i < n; i++) {
            int tempSame = diff;
            int tempDiff = same * (k - 1) + diff * (k - 1);
            same = tempSame;
            diff = tempDiff;
        }
        return diff + same;
    }
}

// 用extra array
public class Solution {
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int[] adjaSame = new int[n];
        int[] adjaNotSame = new int[n];
        
        adjaSame[0] = k;
        adjaNotSame[0] = k;
        adjaSame[1] = k;
        adjaNotSame[1] = k * (k - 1);
        
        for (int i = 2; i < n; i++) {
            adjaSame[i] = adjaNotSame[i - 1];
            adjaNotSame[i] = adjaSame[i - 1] * (k - 1) + adjaNotSame[i - 1] * (k - 1);
        }
        return adjaNotSame[n - 1] + adjaSame[n - 1];
    }
}
