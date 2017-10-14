/*
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False
*/
class Solution {
    public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        int right = (int)Math.sqrt(c);
        int left = 0;
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            }
            else if (sum < c) {
                left++;
            }
            else {
                right--;
            }
        }
        return false;
    }
}
