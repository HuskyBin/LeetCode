/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
*/
public class Solution {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0) {
            return matrix;
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
                else {
                    queue.add(new int[] {i, j})
                }
            }
        }
        int[][] dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < dir.length; i++) {
                int row = cur[0] + dir[i][0];
                int column = cur[1] + dir[i][1];
                if (row < 0 || row >= matrix.size() || column < 0 || column >= matrix.get(0).size() 
                    || matrix.get(row).get(column) <= matrix.get(cur[0]).get(cur[1]) + 1) {
                    continue;
                }
                matrix.get(row).set(column, matrix.get(cur[0]).get(cur[1]) + 1);
                queue.add(new int[] {row, column});
            } 
        }
        return matrix;
    }
}
