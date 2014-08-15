/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
public class Solution {
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] bitSet = new int[32];
        for (int i = 0; i < A.length; i++) {
            int curNum = A[i];
            for (int j = 0; j < 32; j++) {
                if ((curNum & (1 << j)) != 0) {
                    bitSet[j]++;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (bitSet[i] % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }
}
