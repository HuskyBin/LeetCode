/*

We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6
 

Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
*/
class Solution {
    public int shortestPathAllKeys(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0) {
            return 0;
        }
        
        int totalLock = getTotalLock(grid);
        // System.out.println("totalLock " +  totalLock);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                if (grid[i].charAt(j) == '@') {
                    return getMinimalMove(grid, i, j, totalLock);
                }
            }
        }
        return -1;
    }
    
    private int getMinimalMove(String[] grid, int row, int column, int totalLock) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, column, 0, 0});
        Set<String> visited = new HashSet<>();
        visited.add(String.valueOf(0) + ":" + row * grid[0].length() + column);
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] curNode = queue.poll();
            int numberOfOne = getNumberOne(curNode[2]);
            if (numberOfOne == totalLock) {
                return curNode[3];
            }
            for (int[] dir : dirs) {
                int newRow = curNode[0] + dir[0];
                int newColumn = curNode[1] + dir[1];
                int move = curNode[3];
                if (newRow < 0 || newColumn < 0 || newRow >= grid.length || newColumn >= grid[0].length()) {
                    continue;
                }
                if (grid[newRow].charAt(newColumn) == '#' || visited.contains(String.valueOf(curNode[2]) + ":" + newRow * grid[0].length() + newColumn)) {
                    continue;
                }
                    
                if (grid[newRow].charAt(newColumn) == '.' || grid[newRow].charAt(newColumn) == '@') {
                    queue.offer(new int[] {newRow, newColumn, curNode[2], move + 1});
                    visited.add(String.valueOf(curNode[2]) + ":" + newRow * grid[0].length() + newColumn);
                } else if (grid[newRow].charAt(newColumn) >= 'a' && grid[newRow].charAt(newColumn) <= 'f') {
                        
                    int keys = curNode[2] | (1 << (grid[newRow].charAt(newColumn) - 'a'));
                    queue.offer(new int[] {newRow, newColumn, keys, move + 1});
                    visited.add(String.valueOf(keys) + ":" + newRow * grid[0].length() + newColumn);
                } else if (grid[newRow].charAt(newColumn) >= 'A' && grid[newRow].charAt(newColumn) <= 'F') {
                    if ((curNode[2] & (1 << (grid[newRow].charAt(newColumn) - 'A'))) > 0) {
                        queue.offer(new int[] {newRow, newColumn, curNode[2], move + 1});
                        visited.add(String.valueOf(curNode[2]) + ":" + newRow * grid[0].length() + newColumn);
                    }
                }
            }
        }
        return -1;
    }
    
    private int getNumberOne(int number) {
        int result = 0;
        while (number > 0) {
            result++;
            number = number & (number - 1);
        }
        return result;
    }
    
    private int getTotalLock(String[] grid) {
        int result = 0;
        for (String row : grid) {
            for (char c : row.toCharArray()) {
                if (c >= 'A' & c <= 'F') {
                    result++;
                }
            }
        }
        return result;
    }
}
