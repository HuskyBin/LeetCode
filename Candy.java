/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/
public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] result = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i == 0) {
                result[i] = 1;
            }
            else if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            }
            else {
                result[i] = 1;
            }
        }
        
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i == ratings.length - 1) {
                result[i] = Math.max(result[i], 1);
            }
            else if (ratings[i] > ratings[i + 1]){
                result[i] = Math.max(result[i], result[i + 1] + 1);
            }
            else {
                result[i] = Math.max(result[i], 1);
            }
        }
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[i];
        }
        return sum;
    }
}
