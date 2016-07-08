/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
*/
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n <= 0) {
            return 1;
        }
        int sum = 0;
        int digit = n;
        int dp = 10;
        int currentOption = 9;
        int temp = 9;
        for (int i = 2; i <= digit; i++) {
            temp= temp * currentOption;
            dp = temp + dp;
            currentOption--;
        }
        return dp;
    }
}
