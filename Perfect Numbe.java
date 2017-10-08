／*
We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
Note: The input number n will not exceed 100,000,000. (1e8)
*／
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        // 只需要 判断到 Math.sqrt(num) 就可以了，比这大的约数都可以在之前就算出来了
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        return sum == num;
    }
}
