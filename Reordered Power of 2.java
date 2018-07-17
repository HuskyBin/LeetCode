/*
Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
*/
class Solution {
    public boolean reorderedPowerOf2(int N) {
        if (N <= 0) {
            return false;
        }       
        int[] count = getDigitCount(N);
        for (int i = 0; i < 32; i++) {
            if (isSameReorder((1 << i), count)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameReorder(int num1, int[] count2) {
        int[] count1 = getDigitCount(num1);
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getDigitCount(int num) {
        int[] count = new int[10];
        while (num > 0) {
            int digit = num % 10;
            count[digit]++;
            num /= 10;
        }
        return count;
    }
}
