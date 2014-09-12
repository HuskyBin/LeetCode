/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j] == true) {
                    continue;
                }
                if (board[i][j] == 'X') {
                    visited[i][j] = true;
                    continue;
                }
                BfsSearch(board, i, j, visited);
            }
        }
        return;
    }
    
    public void BfsSearch(char[][] board, int row, int column, boolean[][] visited) {
        Queue<Integer> indexQueue = new LinkedList<>();
        indexQueue.offer(row);
        indexQueue.offer(column);
         visited[row][column] = true;
        List<Integer> processedIndex = new ArrayList<>();
        boolean valid = true;
        while (indexQueue.size() > 0) {
            int curRowIndex = indexQueue.poll();
            int curColumnIndex = indexQueue.poll();
            processedIndex.add(curRowIndex);
            processedIndex.add(curColumnIndex);
            if (curRowIndex == 0 || curRowIndex == board.length - 1) {
                valid = false;
            }
            if (curColumnIndex == 0 || curColumnIndex == board[0].length - 1) {
                valid = false;
            }
            if (curRowIndex < board.length - 1 && board[curRowIndex + 1][curColumnIndex] == 'O' && visited[curRowIndex + 1][curColumnIndex] == false) {
                indexQueue.offer(curRowIndex + 1);
                indexQueue.offer(curColumnIndex);
                visited[curRowIndex + 1][curColumnIndex] = true;
            }
            if (curColumnIndex < board[0].length - 1 && board[curRowIndex][curColumnIndex + 1] == 'O' && visited[curRowIndex][curColumnIndex + 1] == false) {
                indexQueue.offer(curRowIndex);
                indexQueue.offer(curColumnIndex + 1);
                visited[curRowIndex][curColumnIndex + 1] = true;
            }
            if (curRowIndex > 0 && board[curRowIndex - 1][curColumnIndex] == 'O' && visited[curRowIndex - 1][curColumnIndex] == false) {
                indexQueue.offer(curRowIndex - 1);
                indexQueue.offer(curColumnIndex);
                visited[curRowIndex - 1][curColumnIndex] = true;
            }
            if (curColumnIndex > 0 && board[curRowIndex][curColumnIndex - 1] == 'O' && visited[curRowIndex][curColumnIndex - 1] == false) {
                indexQueue.offer(curRowIndex);
                indexQueue.offer(curColumnIndex - 1);
                visited[curRowIndex][curColumnIndex - 1] = true;
            }
        }
        if (valid == true) {
            changeBoard(board, processedIndex);
        }
    }
    
    public void changeBoard(char[][] board, List<Integer> processedIndex) {
        for (int i = 0; i < processedIndex.size(); i++) {
            int rowIndex = processedIndex.get(i);
            i++;
            int columnIndex = processedIndex.get(i);
            board[rowIndex][columnIndex] = 'X';
        }
    }
}
