//One dimensional array
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> curList = triangle.get(i);
            for (int j = curList.size() - 1; j >= 0 ; j--) {
                if (j == 0) {
                    dp[j] = curList.get(j) + dp[j];
                }
                else if (j == curList.size() - 1) {
                    dp[j] = curList.get(j) + dp[j - 1];
                }
                else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + curList.get(j);
                }
            }
        }
        int result = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (result > dp[i]) {
                result = dp[i];
            }
        }
        return result;
    }
}
