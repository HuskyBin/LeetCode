/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

// BS
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int min1 = 0;
        int min2 = 0;
        int lastColor = -1;
        for (int i = 0; i < costs.length; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin2 = Integer.MAX_VALUE;
            int curMinColor = -1;
            for (int j = 0; j < costs[0].length; j++) {
                int cost = costs[i][j] + (j == lastColor ? min2 : min1);
                if (cost < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = cost;
                    curMinColor = j;
                }
                else if (cost < curMin2) {
                    curMin2 = cost;
                }
            }
            min1 = curMin1;
            min2 = curMin2;
            lastColor = curMinColor;
        }
        return min1;
    }
}

// MS
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int[] color = new int[costs[0].length];
        for (int i = 0; i < color.length; i++) {
            color[i] = costs[0][i];
        }
        
        for (int i = 1; i < costs.length; i++) {
            int[] nextColor = new int[costs[0].length];
            for (int j = 0; j < costs[0].length; j++) {
                int min = Integer.MAX_VALUE;
                int index = 0;
                while (index < costs[0].length) {
                    if (index != j) {
                        min = Math.min(min, color[index]);
                    }
                    index++;
                }
                nextColor[j] = min + costs[i][j];
            }
            for (int k = 0; k < costs[0].length; k++) {
                color[k] = nextColor[k];
            }
        }
        int result = color[0];
        for (int i = 1; i < color.length; i++) {
            result = Math.min(result, color[i]);
        }
        return result;
    }
}

