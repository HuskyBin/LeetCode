/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public int trailingZeroes(int n) {
        if (n < 0) {
            return -1;
        }
        long result = 0;
        for (long i = 5; n / i > 0; i *= 5) {
            result += n / i;
        }
        return (int)result;
    }
}
