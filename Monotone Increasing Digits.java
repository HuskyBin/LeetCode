/*
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].
*/
class Solution {
    public int monotoneIncreasingDigits(int N) {
        int digit = getDigitNumber(N);
        int decreaseIndex = getFirstDecreaseIndex(N, digit);
        if (decreaseIndex == 0) {
            return N;
        }
        int number = ((N / (int)Math.pow(10, decreaseIndex - 1)) - 1) * (int)Math.pow(10, decreaseIndex - 1) + (int)Math.pow(10, decreaseIndex - 1) - 1;
        return number;
    }
    
    private int getFirstDecreaseIndex(int number, int digit) {
        int digitNumber = digit;
        int lastDigit = number / (int)Math.pow(10, --digit);
        while (digit > 0) {
            int curDigit = (number / (int) Math.pow(10, --digit)) % 10;
            if (curDigit < lastDigit) {
                return digitNumber;
            } else if (curDigit > lastDigit) {
                lastDigit = curDigit;
                digitNumber = digit + 1;
            }
        }
        return 0;
    }
    
    private int getDigitNumber(int number) {
        int digit = 0;
        while (number > 0) {
            number /= 10;
            digit++;
        }
        return digit;
    }
}
