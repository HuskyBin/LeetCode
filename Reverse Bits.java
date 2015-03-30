/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t max = 0xffffffff;
 		uint32_t result = 0;
 		uint32_t digits = 0;
 		while (digits < 32) {
 			int lastBit = (n & 1);
 			if ((max - lastBit) / 2 < result) {
 				return 0;
 			}
 			result = result * 2 + lastBit;
 			n = n >> 1;
 			digits++;
 		}       
 		return result;
    }
};
