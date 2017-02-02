/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

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

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null) {
            return false;
        }   
        
        Set<String> visited = new HashSet<>();
        return findCore(maze, start[0], start[1], destination, visited);
    }
    
    private boolean findCore(int[][] maze, int row, int column, int[] destination, Set<String> visited) {
        if (row == destination[0] && column == destination[1]) {
            return true;
        }
        String keyStr = String.valueOf(row) + ';' + String.valueOf(column);
        if (visited.contains(keyStr)) {
            return false;
        }
        visited.add(keyStr);
        boolean result = false;
        int tempRow = row;
        if ((tempRow - 1) >= 0 && maze[tempRow - 1][column] == 0) {
            while ((tempRow - 1) >= 0 && maze[tempRow - 1][column] == 0) {
                tempRow--;
            }
            result = findCore(maze, tempRow, column, destination, visited);
            if (result) return result;
        }
        tempRow = row;
        if ((tempRow + 1) < maze.length && maze[tempRow + 1][column] == 0) {
            while ((tempRow + 1) < maze.length && maze[tempRow + 1][column] == 0) {
                tempRow++;
            }
            result = findCore(maze, tempRow, column, destination, visited);
            if (result) return result;
        }
        int tempColumn = column;
        if ((tempColumn - 1) >= 0 && maze[row][tempColumn - 1] == 0) {
            while ((tempColumn - 1) >= 0 && maze[row][tempColumn - 1] == 0) {
                tempColumn--;
            }
            result = findCore(maze, row, tempColumn, destination, visited);
            if (result) return result;
        }
        tempColumn = column;
        if ((tempColumn + 1) < maze[0].length && maze[row][tempColumn + 1] == 0) {
            while ((tempColumn + 1) < maze[0].length && maze[row][tempColumn + 1] == 0) {
                tempColumn++;
            }
            result = findCore(maze, row, tempColumn, destination, visited);
            if (result) return result;
        }
        return false;
        
    }
}
