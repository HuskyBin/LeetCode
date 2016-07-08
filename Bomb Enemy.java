/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

*/
public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int result = 0;
        
        int[][] row = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'E') {
                    max++;
                }
                else if (grid[i][j] == 'W') {
                    max = 0;
                }
                else {
                    row[i][j] = max;
                }
            }
            max = 0;
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == 'E') {
                    max++;
                }
                else if (grid[i][j] == 'W') {
                    max = 0;
                }
                else {
                    row[i][j] = max + row[i][j];
                }
            }
        }
        
        for (int j = 0; j < grid[0].length; j++) {
            int[] column = new int[grid.length];
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 'E') {
                    max++;
                }
                else if (grid[i][j] == 'W') {
                    max = 0;
                }
                else {
                    column[i] = max;
                }
            }
            max = 0;
            for (int i = grid.length - 1; i >= 0; i--) {
                if (grid[i][j] == 'E') {
                    max++;
                }
                else if (grid[i][j] == 'W') {
                    max = 0;
                }
                else {
                    column[i] = column[i] + max;
                    result = Math.max(row[i][j] + column[i], result);
                }
            }
        }
        return result;
    }
}
