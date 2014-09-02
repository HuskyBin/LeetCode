/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.
*/
public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        solveSudokuCore(board, 0, 0);
    }
    
    private boolean solveSudokuCore(char[][] board, int rowIndex, int columnIndex) {
        if (rowIndex == board.length - 1 && columnIndex == board[0].length) {
            return true;
        }
        if (columnIndex == board[0].length) {
            return solveSudokuCore(board, rowIndex + 1, 0);
        }
        if (board[rowIndex][columnIndex] != '.') {
            return solveSudokuCore(board, rowIndex, columnIndex + 1);
        }
        for (int i = 1; i <= 9; i++) {
            if (isValid(board, rowIndex, columnIndex, i)) {
                board[rowIndex][columnIndex] = (char)(i + '0');
                if (solveSudokuCore(board, rowIndex, columnIndex + 1) == true) {
                    return true;
                }
                board[rowIndex][columnIndex] = '.';
            }
        }
        return false;
    }
    
    private boolean isValid(char[][] board, int row, int column, int number) {
        for (int i = 0;i < board[0].length; i++) {
            if (Character.getNumericValue(board[row][i]) == number) {
                return false;
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            if (Character.getNumericValue(board[i][column]) == number) {
                return false;
            }
        }
        int cellRow = row / 3;
        int cellColumn = column / 3;
        for (int i = cellRow * 3; i < cellRow * 3 + 3; i++) {
            for (int j = cellColumn * 3; j < cellColumn * 3 + 3; j++) {
                if (Character.getNumericValue(board[i][j]) == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
