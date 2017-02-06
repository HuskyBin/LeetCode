/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
*/
public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> resultList = new ArrayList<>();
        if (m <= 0 || n <= 0 || positions == null) {
            return resultList;
        }
        int[] root = new int[m * n + 1];
        int count = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] position : positions) {
            int curRoot = n * position[0] + position[1] + 1;
            root[curRoot] = curRoot;
            count++;
            for (int[] dir : dirs) {
                int nbRow = position[0] + dir[0];
                int nbColumn = position[1] + dir[1];
                int nb = n* nbRow + nbColumn + 1;
                if (nbRow < 0 || nbRow >= m || nbColumn < 0 || nbColumn >= n || root[nb] == 0) {
                    continue;
                }
                int nbRoot = findRoot(root, nb);
                if (nbRoot != curRoot) {
                    root[curRoot] = nbRoot;
                    curRoot = nbRoot;
                    count--;
                }
            }
            resultList.add(count);
        }
        return resultList;
    }
    
    private int findRoot(int[] root, int node) {
        while (node != root[node]) {
            root[node] = root[root[node]];
            node = root[node];
        }
        return node;
    }
}
