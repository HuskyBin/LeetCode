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
