/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.


*/
public class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] row = new int[n];
        List<Integer> oneResult = new ArrayList<>();
        List<List<Integer>> allResult = new ArrayList<>();
        solveNQueens(n, row, oneResult, allResult, 0);
        return allResult.size();
    }
    
    private void solveNQueens(int n, int[] row, List<Integer> oneResult, List<List<Integer>> allResult, int index) {
        if (index == n) {
            allResult.add(new ArrayList<>(oneResult));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, index)) {
                oneResult.add(i);
                row[index] = i;
                solveNQueens(n, row, oneResult, allResult, index + 1);
                row[index] = 0;
                oneResult.remove(oneResult.size() - 1);
            }
        }
    }
    
    private boolean isValid(int[] row, int num, int index) {
        for (int i = 0; i < index; i++) {
            if (row[i] == num) {
                return false;
            }
            if (Math.abs(row[i] - num) == Math.abs(i - index)) {
                return false;
            }
        }
        return true;
    }
}
