/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Credits:
Special thanks to @amrsaqr for adding this problem and creating all test cases.
*/
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int diff = n - m;
    	int result = n;
    	for (int i = 0; i < 32 && diff > 0; i++) {
    		if ((result & (1 << i)) != 0) {
    			diff -= (int)Math.pow(2.0, i);
    			result &= ~(1 << i);
    		}
    	}
    	return result;
    }
}
