/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/
class Solution {
    int max = 0;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int curResult = dfs(matrix, i, j, map);
                max = Math.max(max, curResult);
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int row, int column, Map<String, Integer> map) {
        String key = String.valueOf(row) + "," + String.valueOf(column);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int curMax = 1;
        for (int i = 0; i < dir.length; i++) {
            int newRow = row + dir[i][0];
            int newColumn = column + dir[i][1];
            if (newRow >= 0 && newRow < matrix.length && newColumn >= 0 && newColumn < matrix[0].length && matrix[newRow][newColumn] > matrix[row][column]) {
                curMax = Math.max(curMax, dfs(matrix, newRow, newColumn, map) + 1);
            }
        }
        map.put(key, curMax);
        return curMax;
    }
}
