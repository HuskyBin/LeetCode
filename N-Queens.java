/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> resultList = new ArrayList<>();
        if (n <= 0) {
            return resultList;
        }
        int[] row = new int[n];
        List<Integer> oneResult = new ArrayList<>();
        List<List<Integer>> allResult = new ArrayList<>();
        solveNQueens(n, row, oneResult, allResult, 0);
        changeIntToString(allResult, resultList);
        return resultList;
    }
    
    private void changeIntToString(List<List<Integer>> allResult, List<String[]> resultList) {
        for (List<Integer> list : allResult) {
            String[] strs = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                int j = 0;
                int number = list.get(i);
                while (j < number) {
                    sb.append('.');
                    j++;
                }
                sb.append('Q');
                j++;
                while (j < list.size()) {
                    sb.append('.');
                    j++;
                }
                strs[i] = sb.toString();
            }
            resultList.add(strs);
        }
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
