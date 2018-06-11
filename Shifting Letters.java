/*
We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9
*/
class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        if (S == null || shifts == null) {
            return "";
        }
        long[] prefixSum = new long[shifts.length];
        for (int i = shifts.length - 1; i >= 0; i --) {
            if (i < shifts.length - 1) {
                prefixSum[i] = shifts[i] + prefixSum[i + 1];
            } else {
                prefixSum[i] = shifts[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            char newChar = generateChar(c, prefixSum[i]);
            sb.append(newChar);
        }
        return sb.toString();
    }
    
    private char generateChar(char c, long shift) {
        char newChar = (char)(c + (shift % 26));
        if (newChar > 'z') {
            return (char)(c - (26 -  (shift % 26)));
        } else {
            return newChar;
        }
    }
}
