/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.


*/
class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] dist = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];
        int totalBuilding = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                totalBuilding++;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[] {i, j});
                boolean[][] visited = new boolean[grid.length][grid[0].length];
                int level = 1;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] cur = queue.poll();
                        for (int m = 0; m < dir.length; m++) {
                            int newI = cur[0] + dir[m][0];
                            int newJ = cur[1] + dir[m][1];
                            if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && grid[newI][newJ] == 0
                            && visited[newI][newJ] == false) {
                                dist[newI][newJ] += level;
                                reach[newI][newJ] += 1;
                                visited[newI][newJ] = true;
                                queue.add(new int[] {newI, newJ});
                            }
                        }
                    }
                    level++;
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (reach[i][j] == totalBuilding && grid[i][j] == 0) {
                    result = Math.min(result, dist[i][j]);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
