/*
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 1.
*/

// 这不是最优解
//  先dfs，算出每一个group的大小，然后把他们的value都赋值成一个unique的index
// 然后对于每一个 o，看上下左右的group index，如果是没见过的index，就加上这个group的value
// 核心思想，每个group只算一遍
class Solution {
    public int largestIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }   
        int result = 0;
        boolean allOne = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    allOne = false;
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    int connect = findConnect(grid, i, j, visited);
                    result = Math.max(result, connect);
                }
            }
        }
        if (allOne) {
            return grid.length * grid[0].length;
        }
        return result;
    }
    
    private int findConnect(int[][] grid, int row, int column, boolean[][] visited) {
        visited[row][column] = true;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int result = 1;
        for (int i = 0; i < dir.length; i++) {
            int newRow = row + dir[i][0];
            int newColumn = column + dir[i][1];
            if (isValidIndex(newRow, newColumn, visited, grid)) {
                result += findConnect(grid, newRow, newColumn, visited);
            }
        }
        return result;
    }
    
    private boolean isValidIndex(int row, int column, boolean[][] visited, int[][] grid) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length) {
            return false;
        }
        return !visited[row][column] && grid[row][column] == 1;
    }
}
