/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?

Credits:
Special thanks to @jeantimex for adding this problem and creating all test cases.
*/
public class Solution {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2) {
            return false;
        }
        for (int i = 1; i <= num.length() / 2; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                String first = num.substring(0, i);
                String second = num.substring(i, j);
                if (isValid(num, first, second, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isValid(String str, String first, String second, int index) {
        if (index == str.length()) {
            return true;
        }
        if ((first.charAt(0) == '0' && first.length() > 1) || (second.charAt(0) == '0' && second.length() > 1)) {
            return false;
        }
        long firstNum = Long.valueOf(first);
        long secondNum = Long.valueOf(second);
        long sum = firstNum + secondNum;
        String sumStr = String.valueOf(sum);
        if (sumStr.length() + index > str.length()) {
            return false;
        }
        if (str.substring(index, index + sumStr.length()).equals(sumStr)) {
            return isValid(str, second, sumStr, index + sumStr.length());
        }
        return false;
    }
}
