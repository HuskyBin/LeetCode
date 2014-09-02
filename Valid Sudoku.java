/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


*/
public class Solution {
    
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int[][] cell = new int[3][3];
        int[] row = new int[board.length];
        int[] column = new int[board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (updateRowOrColumn(row, i, board[i][j]) == false) {
                    return false;
                }
                if (updateRowOrColumn(column, j, board[i][j]) == false) {
                    return false;
                }
                int cellRow = i / 3;
                int cellColumn = j / 3;
                if (updateCell(cell, cellRow, cellColumn, board[i][j]) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    private boolean updateRowOrColumn(int[] number, int index, char cellNumber) {
        if ((number[index] & (1 << Character.getNumericValue(cellNumber))) != 0) {
            return false;
        }
        number[index] |= (1 << Character.getNumericValue(cellNumber));
        return true;
    }
    
    private boolean updateCell(int[][] cell, int cellRow, int cellColumn, char cellNumber) {
        if ((cell[cellRow][cellColumn] & (1 << Character.getNumericValue(cellNumber))) != 0) {
            return false;
        }
        cell[cellRow][cellColumn] |= (1 << Character.getNumericValue(cellNumber));
        return true;
    }
}
