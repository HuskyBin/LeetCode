/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.


After the rain, water are trapped between the blocks. The total volume of water trapped is 4.
*/
class Solution {
    private class Cell {
            public int row;
            public int column;
            public int height;
            
            public Cell(int row, int column, int height) {
                this.row = row;
                this.column = column;
                this.height = height;
            }
        }
    
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int row = heightMap.length;
        int column = heightMap[0].length;
        int result = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            visited[i][0] = true;
            visited[i][column - 1] = true;
            queue.add(new Cell(i, 0, heightMap[i][0]));
            queue.add(new Cell(i, column - 1, heightMap[i][column - 1]));
        }
        
        for (int i = 0; i < column; i++) {
            visited[0][i] = true;
            visited[row - 1][i] = true;
            
            queue.add(new Cell(0, i, heightMap[0][i]));
            queue.add(new Cell(row - 1, i, heightMap[row - 1][i]));
        }
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Cell curCell = queue.poll();
            
            for (int i = 0; i < dir.length; i++) {
                int newRow = curCell.row + dir[i][0];
                int newColumn = curCell.column + dir[i][1];
                if (newRow >= 0 && newRow < row && newColumn >= 0 && newColumn < column && !visited[newRow][newColumn]) {
                    result += Math.max(0, curCell.height - heightMap[newRow][newColumn]);
                    visited[newRow][newColumn] = true;
                    queue.add(new Cell(newRow, newColumn, Math.max(curCell.height, heightMap[newRow][newColumn])));
                }
            }
        }
        return result;
        
    }
}
