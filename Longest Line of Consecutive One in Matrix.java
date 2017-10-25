/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.


*/
class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[][] dir = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    for (int k = 0; k < dir.length; k++) {
                        int count = findLongestLine(M, i, j, dir[k][0], dir[k][1]);
                        max = Math.max(max, count);
                    }
                }
            }
        }
        return max;
    }
    
    private int findLongestLine(int[][] M, int row, int column, int x, int y) {
        if (row >= M.length || column >= M[0].length || row < 0 || column < 0) {
            return 0;
        }
        if (M[row][column] == 0) {
            return 0;
        }

        return findLongestLine(M, row + x, column + y, x, y) + 1;
    }
}
