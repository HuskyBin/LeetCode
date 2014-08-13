public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minValue = prices[0];
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            }
            else {
                if (prices[i] - minValue > result) {
                    result = prices[i] - minValue;
                }
            }
        }
        return result;
    }
}
