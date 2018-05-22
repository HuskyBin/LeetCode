/*
We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20
Explanation: 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 

Note:

1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.
*/
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        if (A == null || K <= 0) {
            return 0.0;
        } 
        double[][] dp = new double[A.length][2];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            dp[i][0] = sum / ((i + 1) * 1.0);
        }
        int newIndex = 0;
        int old = 1;
        for (int k = 2; k <= K; k++) {
            newIndex= 1 - newIndex;
            old = 1 - old;
            for (int i = k - 1; i < A.length; i++) {
                int curSum = 0;
                int count = 0;
                for (int j = i; j > 0; j--) {
                    curSum += A[j];
                    count++;
                    dp[i][newIndex] = Math.max(dp[i][newIndex], dp[j - 1][old] + curSum / (count * 1.0));
                }
            }
        }
        return dp[A.length - 1][newIndex];
    }
}
