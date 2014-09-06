// Update 06/09/2014
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        Map<Character, Boolean> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!map.containsKey(curChar)) {
                map.put(curChar, true);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (map.containsKey(board[i][j])) {
                    boolean result = existCore(board, word, 0, visited, map, i, j);
                    if (result == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    private boolean existCore(char[][] board, 
                              String word, 
                              int len, 
                              boolean[][] visited, 
                              Map<Character, Boolean> map, 
                              int row, 
                              int column) {
        if (len == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            return false;
        }
        if (visited[row][column] == true) {
            return false;
        }
        visited[row][column] = true;
        if (board[row][column] == word.charAt(len)) {
            boolean subResult = existCore(board, word, len + 1, visited, map, row + 1, column);
            if (subResult == true) {
                return true;
            }
            subResult = existCore(board, word, len + 1, visited, map, row - 1, column);
            if (subResult == true) {
                return true;
            }
            subResult = existCore(board, word, len + 1, visited, map, row, column + 1);
            if (subResult == true) {
                return true;
            }
            subResult = existCore(board, word, len + 1, visited, map, row, column - 1);
            if (subResult == true) {
                return true;
            }
        }
        visited[row][column] = false;
        return false;
    }
}



// old version
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return true;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        Map<Character, Integer> strMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (strMap.containsKey(curChar)) {
                strMap.put(curChar, strMap.get(curChar) + 1);
            }
            else {
                strMap.put(curChar, 1);
            }
        }
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j] == false) {
                    if (!strMap.containsKey(board[i][j])) {
                        visited[i][j] = true;
                        continue;
                    }
                    result = searchCore(board, word, i, j, strMap, 0, visited);
                    if (result == true) {
                        return true;
                    }
                }
            }
        }
        return result;
    }

    public boolean searchCore(char[][] board, String word, int row, int column, Map<Character, Integer> strMap, int num, boolean[][] visited) {
        if (num == word.length()) {
            return true;
        }
        if (column == board[0].length || row == board.length || column < 0 || row < 0) {
            return false;
        }
        if (visited[row][column] == true) {
            return false;
        }
        visited[row][column] = true;
        char curChar = board[row][column];
        if (strMap.containsKey(curChar) && curChar == word.charAt(num)) {
            num++;
            boolean subResult = false;
            subResult |= searchCore(board, word, row + 1, column, strMap, num, visited);
            if (subResult == true) {
                return subResult;
            }
            subResult |= searchCore(board, word, row, column + 1, strMap, num, visited);
            if (subResult == true) {
                return subResult;
            }
            subResult |= searchCore(board, word, row - 1, column, strMap, num, visited);
            if (subResult == true) {
                return subResult;
            }
            subResult |= searchCore(board, word, row, column - 1, strMap, num, visited);
            visited[row][column] = false;
            return subResult;
        }
        else {
            visited[row][column] = false;
            return false;
        }
    }
}
