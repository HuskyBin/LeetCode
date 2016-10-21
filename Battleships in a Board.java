/*
Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is not a valid board - as battleships will always have a cell separating between them.
*/
public class Solution {
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int result = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j] == false && board[i][j] == 'X') {
                    markVisited(board, i, j, visited);
                    result++;
                }
            }
        }
        return result;
    }
    
    private void markVisited(char[][] board, int row, int column, boolean[][] visited) {
        int rowIndex = row;
        int columnIndex = column;
        while (rowIndex >= 0 && board[rowIndex][column] == 'X') {
            visited[rowIndex][column] = true;
            rowIndex--;
        }
        rowIndex = row;
        while (rowIndex < board.length && board[rowIndex][column] == 'X') {
            visited[rowIndex][column] = true;
            rowIndex++;
        }
        while (columnIndex >= 0 && board[row][columnIndex] == 'X') {
            visited[row][columnIndex] = true;
            columnIndex--;
        }
        columnIndex = column;
        while (columnIndex < board[0].length && board[row][columnIndex] == 'X') {
            visited[row][columnIndex] = true;
            columnIndex++;
        }
    }
}
