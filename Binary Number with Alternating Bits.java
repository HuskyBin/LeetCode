／*
Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.
*／
class Solution {
    public boolean hasAlternatingBits(int n) {
        return  ((n ^= (n >> 2)) & (n - 1)) == 0;
    }
}


// 网上解法
bool hasAlternatingBits(int n) {
    return !((n ^= n/4) & n-1);
}
Xor the number with itself shifted right twice and check whether everything after the leading 1-bit became/stayed 0. 
Xor is 0 iff the bits are equal, so we get 0-bits iff the pair of leading 1-bit and the 0-bit in front of it are 
repeated until the end.

    000101010
  ^ 000001010
  = 000100000
Solution 2 - Complete Bits
bool hasAlternatingBits(int n) {
    return !((n ^= n/2) & n+1);
}
Xor the number with itself shifted right once and check whether everything after the leading 1-bit became/stayed 1. 
Xor is 1 iff the bits differ, so we get 1-bits iff starting with the leading 1-bit, the bits alternate between 1 and 0.
