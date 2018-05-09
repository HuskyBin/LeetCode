/*
Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 

Note:
You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
*/
class Solution {
     public int findMinStep(String board, String hand) {
        if (board == null || hand == null) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : hand.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int result = dfsCore(board, map);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private int dfsCore(String board, Map<Character, Integer> map) {
        String newBoard = eliminate(board);
        System.out.println(board + " : " + newBoard);
        if (newBoard.length() == 0) {
            return 0;
        }
        int start = 0;
        int count = Integer.MAX_VALUE;
        while (start < newBoard.length()) {
            int end = start + 1;
            while (end < newBoard.length() && newBoard.charAt(end) == newBoard.charAt(start)) {
                end++;
            }
            int need = 3 - (end - start);
            char curChar = newBoard.charAt(start);
            if (map.containsKey(curChar) && map.get(curChar) >= need) {
                map.put(curChar, map.get(curChar) - need);
                int subResult = dfsCore(newBoard.substring(0, start) + newBoard.substring(end), map);
                if (subResult != Integer.MAX_VALUE) {
                    count = Math.min(count, subResult + need);
                }
                map.put(curChar, map.get(curChar) + need);
            }
            start = end;
        }
        return count;
    }
    
    private String eliminate(String board) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < board.length()) {
            int end = start + 1;
            while (end < board.length() && board.charAt(start) == board.charAt(end)) {
                end++;
            }
            if (end - start < 3) {
                while (start < end) {
                    sb.append(board.charAt(start));
                    start++;
                }
            } else {
                start = end;
            }
        }
        if (sb.length() == board.length()) {
            return sb.toString();
        }
        return eliminate(sb.toString());
    }
}
