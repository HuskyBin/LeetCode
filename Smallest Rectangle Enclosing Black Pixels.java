/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/
public class Solution {
    public int minRow = Integer.MAX_VALUE;
    public int maxRow = Integer.MIN_VALUE;
    public int minColumn = Integer.MAX_VALUE;
    public int maxColumn = Integer.MIN_VALUE;
    
    public int minArea(char[][] image, int x, int y) {
        if (image == null) {
            return 0;
        }    
        boolean[][] visited = new boolean[image.length][image[0].length];
        minAreaHelper(image, x, y, visited);
        return (maxRow - minRow + 1) * (maxColumn - minColumn + 1);
    }
    
    private void minAreaHelper(char[][] image, int row, int column, boolean[][] visited) {
        if (visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        minRow = Math.min(minRow, row);
        maxRow = Math.max(maxRow, row);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);
        for (int i = 0; i < dir.length; i++) {
            // helper(image, row, column, dir[0], dir[1], visited)
            int newRow = row + dir[i][0];
            int newColumn = column + dir[i][1];
            if (newRow >= 0 && newRow < image.length && newColumn >= 0 && newColumn < image[0].length) {
                if (image[newRow][newColumn] == '1') {
                    minAreaHelper(image, newRow, newColumn, visited);
                }
            }
        }
    }
}
