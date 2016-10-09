/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

// DFS
public class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> resultList = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return resultList;
        }
        boolean[][] flowPacific = new boolean[matrix.length][matrix[0].length];
        boolean[][] flowAtlantic = new boolean[matrix.length][matrix[0].length];
        int[][] visitedPacific = new int[matrix.length][matrix[0].length];
        int[][] visitedAtlantic = new int[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            flowPacific[i][0] = true;
            flowAtlantic[i][matrix[0].length - 1] = true;
            visitedPacific[i][0] = 1;
            visitedAtlantic[i][matrix[0].length - 1] = 1;
        }
        
        for (int i = 0; i < matrix[0].length; i++) {
            flowPacific[0][i] = true;
            flowAtlantic[matrix.length -1][i] = true;
            visitedPacific[0][i] = 1;
            visitedAtlantic[matrix.length - 1][i] = 1;
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (visitedPacific[i][j] != 1) {
                    waterFlowCore(matrix, flowPacific, visitedPacific, i, j);
                    visitedPacific[i][j] = 1;
                }
                if (visitedAtlantic[i][j] != 1) {
                    waterFlowCore(matrix, flowAtlantic, visitedAtlantic, i, j);
                    visitedAtlantic[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (flowPacific[i][j] && flowAtlantic[i][j]) {
                    int[] cell = new int[2];
                    cell[0] = i;
                    cell[1] = j;
                    resultList.add(cell);
                }
            }
        }
        return resultList;
    }
    
    private boolean waterFlowCore(int[][] matrix, boolean[][] flow, int[][] visited, int row, int column) {
        if (row < 0 || row >= matrix.length || column < 0 || column >= matrix[0].length) {
            return false;
        }
        if (visited[row][column] == 1) {
            return flow[row][column];
        }
        visited[row][column] = -1;
        boolean result = false;
        if (row > 0 && matrix[row][column] >= matrix[row - 1][column] && visited[row - 1][column] != -1) {
            result = waterFlowCore(matrix, flow, visited, row - 1, column);
        }
        if (row < matrix.length - 1 && matrix[row][column] >= matrix[row + 1][column] && visited[row + 1][column] != -1) {
            result = result || waterFlowCore(matrix, flow, visited, row + 1, column);
        }
        if (column > 0 && matrix[row][column] >= matrix[row][column - 1] && visited[row][column - 1] != -1) {
            result = result || waterFlowCore(matrix, flow, visited, row, column - 1);
        }
        if (column < matrix[0].length - 1 && matrix[row][column] >= matrix[row][column + 1] && visited[row][column + 1] != -1) {
            result = result || waterFlowCore(matrix, flow, visited, row, column + 1);
        }
        visited[row][column] = 0;
        flow[row][column] = result;
        return result;
    }
}
