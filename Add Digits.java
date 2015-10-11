/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
*/
public class Solution {
    public int addDigits(int num) {
        while (num > 9) {
            int temp = num;
            int sum = 0;
            while (temp > 0){
                sum += temp % 10;
                temp /= 10;
            }
            num = sum;
        }
        return num;
    }
}
