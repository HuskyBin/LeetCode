/*
Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].


当n=2时，数字可选区间就是(9, 99]. 最大的product 就是 99*99 = 9801. 最大的位数是2n 肯定是个偶数. 

取前一半firstHalf '98', 再拼接'98'的reverse '89'组成新的palindrome '9889'. 然后i在(9,99]之间挨个试palindrome % i == 0.

试不出来firstHalf--, 变'97'重来.

*/
class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int high = (int)Math.pow(10, n) - 1;
        int low = high / 10;
        for (int i = high; i > low; i--) {
            long palindrome = createPalindrome(i);
            
            for (int j = high; j > low; j--) {
                if (palindrome / j > high) {
                    break;
                }
                if (palindrome % j == 0) {
                    return (int)(palindrome % 1337);
                }
            }
        }
        return -1;
    }
    
    private long createPalindrome(int num) {
        String str = num + new StringBuilder(String.valueOf(num)).reverse().toString();
        return Long.valueOf(str);
    }
}
