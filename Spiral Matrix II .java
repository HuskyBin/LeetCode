
/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
public class Solution {
    public int[][] generateMatrix(int n) {
        int number = 1;
        if (n < 0) {
            return null;
        }
        int[][] matrix = new int[n][n];
        int rowBegin = 0;
        int rowEnd = n - 1;
        int columnBegin = 0;
        int columnEnd = n - 1;
        
        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            for (int i = columnBegin; i <= columnEnd && rowBegin <= rowEnd; i++) {
                matrix[rowBegin][i] = number;
                number++;
            }
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd && columnBegin <= columnEnd; i++) {
                matrix[i][columnEnd] = number;
                number++;
            }
            columnEnd--;
            for (int i = columnEnd; i >= columnBegin && rowBegin <= rowEnd; i--) {
                matrix[rowEnd][i] = number;
                number++;
            }
            rowEnd--;
            for (int i = rowEnd; i >= rowBegin && columnBegin <= columnEnd; i--) {
                matrix[i][columnBegin] = number;
                number++;
            }
            columnBegin++;
        }
        return matrix;
    }
}
