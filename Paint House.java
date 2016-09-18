/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/
public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int curRed = costs[0][0];
        int curBlue = costs[0][1];
        int curGreen = costs[0][2];
        int nextRed = curRed;
        int nextBlue = curBlue;
        int nextGreen = curGreen;
        for (int i = 1; i < costs.length; i++) {
            nextRed = Math.min(curBlue + costs[i][0], curGreen + costs[i][0]);
            nextBlue = Math.min(curRed + costs[i][1], curGreen + costs[i][1]);
            nextGreen = Math.min(curRed + costs[i][2], curBlue + costs[i][2]);
            curBlue = nextBlue;
            curRed = nextRed;
            curGreen = nextGreen;
        }
        return Math.min(nextGreen,Math.min(nextRed, nextBlue));
    }
}
