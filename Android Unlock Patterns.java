/*
Total Accepted: 11609
Total Submissions: 26916
Difficulty: Medium
Contributors: Admin
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
*/
public class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] jump = new int[10][10];
        jump[1][3] = 2;
        jump[3][1] = 2;
        jump[6][4] = 5;
        jump[4][6] = 5;
        jump[7][9] = 8;
        jump[9][7] = 8;
        jump[1][7] = 4;
        jump[7][1] = 4;
        jump[2][8] = 5;
        jump[8][2] = 5;
        jump[3][9] = 6;
        jump[9][3] = 6;
        jump[1][9] = 5;
        jump[9][1] = 5;
        jump[3][7] = 5;
        jump[7][3] = 5;
        boolean[] visited = new boolean[10];
        int result = 0;
        result += dfs(1, visited, jump, m, n, 1) * 4;
        result += dfs(2, visited, jump, m, n, 1) * 4;
        result += dfs(5, visited, jump, m, n, 1);
        return result;
    }
    
    private int dfs(int node, boolean[] visited, int[][] jump, int m, int n, int len) {
        int cnt = 0;
        if (len >= m && len <= n) {
            cnt++;
        }
        if (len > n) {
            return cnt;
        }
        visited[node] = true;
        for (int i = 1; i <= 9; i++) {
            if (visited[i] == false && (jump[node][i] == 0 || visited[jump[node][i]] == true)) {
                cnt += dfs(i, visited, jump, m, n, len + 1);
            }
        }
        visited[node] = false;
        return cnt;
    }
}
