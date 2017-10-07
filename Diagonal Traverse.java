／*
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
*／
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int row = 0;
        int column = 0;
        int d = 0;
        int[][] dir = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][column];
            row += dir[d][0];
            column += dir[d][1];
            ／／ 必须是先判断 大于m或n，再判断大于0， 因为当达到对角线顶点的时候，要确保row 或 column先加回来了，就不会再进去小于
            ／／ 0 的判断里面了
            if (row >= m) {
                row = m - 1;
                column += 2;
                d = 1 - d;
            }
            if (column >= n) {
                column = n - 1;
                row += 2;
                d = 1 - d;
            }
            if (row < 0) {
                row = 0;
                d = 1 - d;
            }
            if (column < 0) {
                column = 0;
                d = 1 - d;
            }
        }
        return result;
    }
}
