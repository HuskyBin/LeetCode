/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxValue = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            int[] sum = new int[matrix[0].length];
            boolean[] zero = new boolean[matrix[0].length];
            for (int n = i; n >= 0; n--) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (zero[j] == false && matrix[n][j] == '1') {
                        sum[j] += Character.getNumericValue(matrix[n][j]);
                    }
                    else if (matrix[n][j] == '0') {
                        zero[j] = true;
                    }
                }
            }
            maxValue = Math.max(maxValue, getMaxValue(sum));
        }
        return maxValue;
    }

    
    private int getMaxValue(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            if (i < height.length - 1 && height[i] <= height[i + 1]) {
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                min = Math.min(min, height[j]);
                max = Math.max(max, min * (i - j + 1));
            }
        }
        return max;
    }
}
