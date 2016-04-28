/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Show Hint 
Subscribe to see which companies asked this question
*/
public class Solution {
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        long mod = 1;
        int count = 0;
        while (n >= mod) {
            long front = n / (mod * 10);
            long rear = n % mod;
            int curDigit = (int)((n % (mod * 10)) / mod);
            
            if (curDigit > 1) {
                count += (front + 1) * mod;
            }
            else if (curDigit == 1) {
                count += front * mod + rear + 1;
            }
            else {
                count += front * mod;
            }
            mod *= 10;
        }
        return count;
    }
}
