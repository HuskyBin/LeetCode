/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
//  难点； visited 的map里只记录，遇到墙后的点，中间路过的点不用记录，因为可以再经过它
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || destination == null) {
            return 0;
        }
        Queue<Integer> rowQueue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();
        Queue<String> direction = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        rowQueue.add(start[0]);
        columnQueue.add(start[1]);
        direction.add(" ");
        set.add(String.valueOf(start[0]) + "," + String.valueOf(start[1]) + "," + " ");
        int distance = 0;
        while (!rowQueue.isEmpty()) {
            int size = rowQueue.size();
            while (size > 0) {
                int curRow = rowQueue.poll();
                int curColumn = columnQueue.poll();
                
                String curDir = direction.poll();   
                int newRow = -1;
                int newColumn = -1;
                String newDir = "";
                if (curDir.equals("01")) {
                    newRow = curRow;
                    newColumn = curColumn + 1;
                    newDir = curDir;
                }
                else if (curDir.equals("0-1")) {
                    newRow = curRow;
                    newColumn = curColumn - 1;
                    newDir = curDir;
                }
                else if (curDir.equals("10")) {
                    newRow = curRow + 1;
                    newColumn = curColumn;
                    newDir = curDir;
                }
                else if (curDir.equals("-10")) {
                    newRow = curRow - 1;
                    newColumn = curColumn;
                    newDir = curDir;
                }
                if (newRow >= 0 && newRow < maze.length && newColumn >= 0 && newColumn < maze[0].length && maze[newRow][newColumn] == 0) {
                    if (set.contains(String.valueOf(newRow) + "," + String.valueOf(newColumn) + "," + " ") == false) {
                        rowQueue.add(newRow);
                        columnQueue.add(newColumn);
                        direction.add(newDir);
                    }
                }
                else if (curRow == destination[0] && curColumn == destination[1]) {
                    return distance;
                }
                else {
                    set.add(String.valueOf(curRow) + "," + String.valueOf(curColumn) + "," + " ");
                    for (int i = 0; i < dir.length; i++) {
                        int nextRow = curRow + dir[i][0];
                        int nextColumn = curColumn + dir[i][1];
                        if (nextRow >= 0 && nextRow < maze.length && nextColumn >= 0 && nextColumn < maze[0].length && maze[nextRow][nextColumn] == 0 && !set.contains(String.valueOf(nextRow) + "," + String.valueOf(nextColumn) + "," + " ") ) {
                            rowQueue.add(nextRow);
                            columnQueue.add(nextColumn);
                            direction.add(String.valueOf(dir[i][0]) + String.valueOf(dir[i][1]));
                        }
                    }
                }
                size--;
                
            }
            distance++;
        }
        return -1;
    }
}
