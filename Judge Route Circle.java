/*
Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false

*/
class Solution {
    public boolean judgeCircle(String moves) {
        int upCount = 0;
        int leftCount = 0;
        for (char move : moves.toCharArray()) {
            if (move == 'R') {
                leftCount--;
            }
            else if (move == 'L') {
                leftCount++;
            }
            else if (move == 'U') {
                upCount++;
            }
            else {
                upCount--;
            }
        }
        return upCount == 0 && leftCount == 0;
    }
}
