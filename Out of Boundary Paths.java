/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].

*/

class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        if (m < 0 || n < 0 || N < 0) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        return dfs(m, n, N, i, j, map);
    }
    
    private int dfs(int m, int n, int N, int row, int column, Map<String, Integer> map) {
        if (row < 0 || row >= m || column < 0 || column >= n) {
            if (N >= 0) {
                return 1;
            }
        }
        if (N <= 0) {
            return 0;
        }
        int mod = 1000000007;
        String key = String.valueOf(row) + "," + String.valueOf(column) + "," + String.valueOf(N);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int sum = 0;
        for (int i = 0; i < dir.length; i++) {
            int newRow = row + dir[i][0];
            int newColumn = column + dir[i][1];
            sum += dfs(m, n, N - 1, newRow, newColumn, map) % mod;
            sum %= mod;
        }
        map.put(key, sum);
        return sum;
    }
}
