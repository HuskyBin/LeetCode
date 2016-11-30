/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

*/

public class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int result = 0;
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result = countPerimeter(grid, i, j, visited);
                    return result;
                }
            }
        }
        return result;
    }
    
    private int countPerimeter(int[][] grid, int row, int column, boolean[][] visited) {
        if (row < 0 || row >= grid.length) {
            return 1;
        }
        else if (column < 0 || column >= grid[0].length) {
            return 1;
        }
        
        if (visited[row][column] == true) {
            return 0;
        }
        int result = 0;
        if (grid[row][column] == 1) {
            visited[row][column] = true;
            return countPerimeter(grid, row + 1, column, visited) + countPerimeter(grid, row - 1, column, visited) + countPerimeter(grid, row, column + 1, visited) + countPerimeter(grid, row, column - 1, visited);
        }
        else {
            return 1;
        }
    }
}
