/*
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes: 

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
*/
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return 0;
        }
        int len = A.length;
        int[][] count = new int[2 * len][2 * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (A[i][j] == 1) {
                    for (int k = 0; k < len; k++) {
                        for (int m = 0; m < len; m++) {
                            if (B[k][m] == 1) {
                                count[i - k + len][j - m + len]++;
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[0].length; j++) {
                result = Math.max(result, count[i][j]);
            }
        }
        return result;
    }
}
