/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/
public class NumMatrix {

    private int[][] matrixArr;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        this.matrixArr = new int[matrix.length][matrix[0].length];
        matrixArr[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            matrixArr[i][0] += matrix[i][0] + matrixArr[i - 1][0];
        }
        for (int i = 1; i < matrix[0].length; i++) {
            matrixArr[0][i] += matrix[0][i] + matrixArr[0][i - 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrixArr[i][j] = matrixArr[i - 1][j] + matrixArr[i][j - 1] - matrixArr[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (matrixArr == null) {
            return 0;
        }
        if (row1 < 0 || row1 >= matrixArr.length || row2 < 0 || row2 >= matrixArr.length || col1 < 0 || col1 >= matrixArr[0].length || col2  < 0 || col2 >= matrixArr[0].length) {
            return 0;
        }
        if (row1 == 0 && col1 == 0) {
            return matrixArr[row2][col2];
        }

        int downLeft = ((col1 - 1) < 0) ? 0 : matrixArr[row2][col1 - 1];
        int upperRight = ((row1 - 1) < 0) ? 0 : matrixArr[row1 - 1][col2];
        int upperLeft = 0;
        if ((row1 - 1) >= 0 && (col1 - 1) >= 0) {
            upperLeft = matrixArr[row1 - 1][col1 - 1];
        }
        return matrixArr[row2][col2] - downLeft - upperRight + upperLeft;
    }
}
