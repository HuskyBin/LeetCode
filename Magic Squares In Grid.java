/*
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

 

Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15

*/
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int result = 0;
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        for (int i = 0; i <= rowLength - 3; i++) {
            for (int j = 0; j <= columnLength - 3; j++) {
                if (isMagicSquare(grid, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }
    
    private boolean isMagicSquare(int[][] grid, int row, int column) {
        int[] rowSum = new int[3];
        int[] columnSum = new int[3];
        int[] diagSum = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (grid[i][j] <= 0 || grid[i][j] >= 10) {
                    return false;
                }
                if (set.contains(grid[i][j])) {
                    return false;
                }
                set.add(grid[i][j]);
                rowSum[i - row] += grid[i][j];
                columnSum[j - column] += grid[i][j];
                int rowDelta = i - row;
                int columnDelta = j - column;
                if (rowDelta == columnDelta) {
                    diagSum[0] += grid[i][j];
                } 
                if ((rowDelta + columnDelta) == 2) {
                    diagSum[1] += grid[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (rowSum[i] != columnSum[0]) {
                return false;
            }
            if (columnSum[i] != columnSum[0]) {
                return false;
            }
        }
        return (diagSum[0] == diagSum[1] && diagSum[0] == columnSum[0]);
    }
}
