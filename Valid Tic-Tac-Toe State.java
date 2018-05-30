/*
A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
*/
class Solution {
    public boolean validTicTacToe(String[] board) {
        if (board == null) {
            return false;
        }
        int xCount = getCount(board, 'X');
        int oCount = getCount(board, 'O');
        boolean isXWin = isWin(board, 'X');
        boolean isOWin = isWin(board, 'O');
        if (isXWin) {
            return isOWin == false && xCount == oCount + 1;
        }
        if (isOWin) {
            return isXWin == false && xCount == oCount;
        }
        return oCount <= xCount && oCount >= xCount - 1;
    }
    
    private int getCount(String[] board, char role) {
        int count = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == role) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isWin(String[] board, char role) {
        for (int i = 0; i < 3; i++) {
            String row = board[i];
            String roleRow = "" + role + role + role;
            if (row.equals(roleRow)) {
                return true;
            }
            String column = "";
            for (int j = 0; j < 3; j++) {
                column += board[j].charAt(i);
            }
            if (column.equals(roleRow)) {
                return true;
            }
        }
        
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2) && board[2].charAt(2) == role) {
            return true;
        }
        if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0) && board[2].charAt(0) == role) {
            return true;
        }
        return false;
    }
}
