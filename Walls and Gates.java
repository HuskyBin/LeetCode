/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    findGate(rooms, i, j);
                }
            }
        }
    }
    
    private void findGate(int[][] rooms, int row, int column) {
        int max = 2147483647;
        Queue<Integer> queue = new LinkedList<>();
        
        int distance = 1;
        boolean visited[][] = new boolean[rooms.length][rooms[0].length];
        queue.add(row);
        queue.add(column);
        queue.add(-1);
        visited[row][column] = true;
        while (queue.size() > 0) {

            while (queue.peek() != -1) {
                int curRow = queue.poll();
                int curColumn = queue.poll();
                if (curRow > 0 && rooms[curRow - 1][curColumn] != -1 && rooms[curRow - 1][curColumn] != 0 && visited[curRow - 1][curColumn] != true) {
                    queue.add(curRow - 1);
                    queue.add(curColumn);
                    rooms[curRow - 1][curColumn] = Math.min(rooms[curRow - 1][curColumn], distance);
                    visited[curRow - 1][curColumn] = true;
                } 
                if (curRow < rooms.length - 1 && rooms[curRow + 1][curColumn] != -1 && rooms[curRow + 1][curColumn] != 0 && visited[curRow + 1][curColumn] != true) {
                    queue.add(curRow + 1);
                    queue.add(curColumn);
                    rooms[curRow + 1][curColumn] = Math.min(rooms[curRow + 1][curColumn], distance);
                    visited[curRow + 1][curColumn] = true;
                }  
                if (curColumn > 0 && rooms[curRow][curColumn - 1] != -1 && rooms[curRow][curColumn - 1] != 0 && visited[curRow][curColumn - 1] != true) {
                    queue.add(curRow);
                    queue.add(curColumn - 1);
                    rooms[curRow][curColumn - 1] = Math.min(rooms[curRow][curColumn - 1], distance);
                    visited[curRow][curColumn - 1] = true;
                }  
                if (curColumn < rooms[0].length - 1 && rooms[curRow][curColumn + 1] != -1 && rooms[curRow][curColumn + 1] != 0 && visited[curRow][curColumn + 1] != true) {
                    queue.add(curRow);
                    queue.add(curColumn + 1);
                    rooms[curRow][curColumn + 1] = Math.min(rooms[curRow][curColumn + 1], distance);
                    visited[curRow][curColumn + 1] = true;
                }  
            }
            queue.poll();
            if (queue.size() > 0) {
                queue.add(-1);
            }
            distance++;
        }
    }
}


// DFS

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    findGate(rooms, i, j, 0);
                }
            }
        }
    }
    
    
    private void findGate(int[][] rooms, int row, int column, int dis) {
        if (row < 0 || column < 0 || row >= rooms.length || column>= rooms[0].length || rooms[row][column] < dis) {
            return;
        }
        rooms[row][column] = dis;
        findGate(rooms, row + 1, column, dis + 1);
        findGate(rooms, row - 1, column, dis + 1);
        findGate(rooms, row, column - 1, dis + 1);
        findGate(rooms, row, column + 1, dis + 1);
    }
}
