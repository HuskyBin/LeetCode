/*
A positive integer is magical if it is divisible by either A or B.

Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: N = 1, A = 2, B = 3
Output: 2
Example 2:

Input: N = 4, A = 2, B = 3
Output: 6
Example 3:

Input: N = 5, A = 2, B = 4
Output: 10
Example 4:

Input: N = 3, A = 6, B = 4
Output: 8
 

Note:

1 <= N <= 10^9
2 <= A <= 40000
2 <= B <= 40000
*/
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        long start = 1;
        long end = Long.MAX_VALUE;
        long result = -1;
        int mod = 1000000000 + 7;
        int gcd = gcd(A, B);
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long count = (mid / A + mid / B - mid / (A * B / gcd));
            if (count >= N) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (int)(result % mod);
    }
    
    public int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
