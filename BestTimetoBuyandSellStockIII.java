public class Solution {
    public int maxProfit(int[] prices) {
       if (prices == null || prices.length == 0) {
            return 0;
       }
       int[] leftToRight = new int[prices.length];
       int[] rightToLeft = new int[prices.length];

       int smallIndex = 0;
       int maxValue = 0;
       for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[smallIndex]) {
                smallIndex = i;
            }
            else {
                maxValue = Math.max(maxValue, prices[i] - prices[smallIndex]);
            }
            leftToRight[i] = maxValue;
       }

       int maxIndex = prices.length - 1;
       maxValue = 0;

       for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] > prices[maxIndex]) {
                maxIndex = i;
            }
            else {
                maxValue = Math.max(maxValue, prices[maxIndex] - prices[i]);
            }
            rightToLeft[i] = maxValue;
       }

       maxValue = 0;
       for (int i = 0; i < prices.length; i++) {
            if (maxValue < leftToRight[i] + rightToLeft[i]) {
                maxValue = leftToRight[i] + rightToLeft[i];
            }
       }
       return maxValue;
    }
}
