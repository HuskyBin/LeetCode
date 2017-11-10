
/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
Credits:
Special thanks to @fujiaozhu for adding this problem and creating all test cases.


*/
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[] array = new int[column];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            Arrays.fill(array, 0);
            for (int j = i; j < row; j++) {
                for (int m = 0; m < column; m++) {
                    array[m] += matrix[j][m];
                }
                int curValue = findMax(array, k);
                max = Math.max(max, curValue);
            }
        }
        return max;
    }
    
    private int findMax(int[] array, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            Integer gap = set.ceiling(sum - k);
            if (gap != null) max = Math.max(max, sum - gap);
            set.add(sum);
        }
        return max;
    }
}
