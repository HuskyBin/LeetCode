/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return resultList;
        }
        
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int columnBegin = 0;
        int columnEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            for (int i = columnBegin; i <= columnEnd && rowBegin <= rowEnd; i++) {
                resultList.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd && columnBegin <= columnEnd; i++) {
                resultList.add(matrix[i][columnEnd]);
            }
            columnEnd--;
            for (int i = columnEnd; i >= columnBegin && rowBegin <= rowEnd; i--) {
                resultList.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            for (int i = rowEnd; i >= rowBegin && columnBegin <= columnEnd; i--) {
                resultList.add(matrix[i][columnBegin]);
            }
            columnBegin++;
        }
        return resultList;
    }
}
