/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.
*/
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Number> heap = new PriorityQueue<>();
        
        heap.add(new Number(0, 0, matrix[0][0]));
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Number result = null;
        visited[0][0] = true;
        while (k > 0) {
            result = heap.poll();
            int curRow = result.row;
            int curColumn = result.column;
            if (curRow < matrix.length - 1 && (visited[curRow + 1][curColumn] == false)) {
                heap.add(new Number(curRow + 1, curColumn, matrix[curRow + 1][curColumn]));
                visited[curRow + 1][curColumn] = true;
            }
            if (curColumn < matrix[0].length - 1 && (visited[curRow][curColumn + 1] == false)) {
                heap.add(new Number(curRow, curColumn + 1, matrix[curRow][curColumn + 1]));
                visited[curRow][curColumn + 1] = true;
            }
            k--;
        }
        return result.value;
    }
    
    class Number implements Comparable<Number> {
        public int row;
        public int column;
        public int value;
        
        public Number(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
        
        public int compareTo(Number a) {
            return this.value - a.value;
        }
    }
}
