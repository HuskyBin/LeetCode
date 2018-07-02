/*
We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
*/
class Solution {
    public int matrixScore(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int row = A.length;
        int column = A[0].length;
        int result = (1 << (column - 1)) * row;
        for (int i = 1; i < column; i++) {
            int cur = 0;
            for (int j = 0; j < row; j++) {
                cur += A[j][i] == A[j][0] ? 1 : 0;
            }
            result += Math.max(cur, row - cur) * (1 << (column - 1 - i));
        }
        return result;
    }
}
