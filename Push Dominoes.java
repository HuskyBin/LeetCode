/*
There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Note:

0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'
*/
class Solution {
    public String pushDominoes(String dominoes) {
        if (dominoes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int[] left = new int[dominoes.length()];
        int[] right = new int[dominoes.length()];
        int start = 0;
        int count = -1;
        while (start < dominoes.length()) {
            if (dominoes.charAt(start) == 'L') {
                count = -1;
            } else if (dominoes.charAt(start) == 'R') {
                count = 0;
            } else if (count != -1) {
                count++;
                right[start] = count;
            }
            start++;
        }
        
        count = -1;
        int end = dominoes.length() - 1;
        while (end >= 0) {
            if (dominoes.charAt(end) == 'L') {
                count = 0;
            } else if (dominoes.charAt(end) == 'R') {
                count = -1;
            } else if (count != -1) {
                count++;
                left[end] = count;    
            }
            end--;
        }
        
        for (int i = 0; i < dominoes.length(); i++) {
            if (left[i] > 0 && right[i] > 0) {
                if (left[i] < right[i]) {
                    sb.append('L');
                } else if (left[i] > right[i]) {
                    sb.append('R');
                } else {
                    sb.append('.');
                }
            } else if (left[i] > 0 || right[i] > 0) {
                char c = left[i] > 0 ? 'L' : 'R';
                sb.append(c);
            } else {
                sb.append(dominoes.charAt(i));
            }
        }
        return sb.toString();
    }
}
