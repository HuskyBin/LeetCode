/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
*/
class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Set<String> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == false) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, visited, i, j, 'o', sb);
                    if (!set.contains(sb.toString())) {
                        result++;
                        set.add(sb.toString());
                    }
                }
            }
        }
        return result;
    }
    
    private void dfs(int[][] grid, boolean[][] visited, int x, int y, char dir, StringBuilder sb) {
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || visited[x][y] == true || grid[x][y] == 0) {
            return;
        }
        visited[x][y] = true;
        sb.append(dir);
        dfs(grid, visited, x + 1, y, 'u', sb);
        dfs(grid, visited, x - 1, y, 'd', sb);
        dfs(grid, visited, x, y + 1, 'r', sb);
        dfs(grid, visited, x, y - 1, 'l', sb);
        sb.append('b');
    }
}
