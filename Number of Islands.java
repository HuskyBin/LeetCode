/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
        	return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
        	for (int j = 0; j < grid[0].length; j++) {
        		if (visited[i][j] == true || grid[i][j] != '1') {
        			continue;
        		}
        		numIslandsCore(grid, visited, i, j);
        		sum++;
        	}
        }
        return sum;
    }

    private void numIslandsCore(char[][] grid, boolean[][] visited, int x, int y) {
    	if (x < 0 || x >= grid.length) {
    		return;
    	}
    	if (y < 0 || y >= grid[0].length) {
    		return;
    	}
    	if (grid[x][y] != '1' || visited[x][y] == true) {
    		return;
    	}
    	visited[x][y] = true;
    	numIslandsCore(grid, visited, x + 1, y);
    	numIslandsCore(grid, visited, x, y + 1);
    	numIslandsCore(grid, visited, x - 1, y);
    	numIslandsCore(grid, visited, x, y - 1);
    }
}
