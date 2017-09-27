/*
Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
Credits:
Special thanks to @Stomach_ache for adding this problem and creating all test cases.Super Pow


Idea:

One knowledge: ab % k = (a%k)(b%k)%k
Since the power here is an array, we'd better handle it digit by digit.
One observation:
a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
Looks complicated? Let me put it other way:
Suppose f(a, b) calculates a^b % k; Then translate above formula to using f :
f(a,1234567) = f(a, 1234560) * f(a, 7) % k = f(f(a, 123456),10) * f(a,7)%k;
Implementation of this idea:

*/
class Solution {
    public int base = 1337;
    public int superPow(int a, int[] b) {
        return helper(a, b, b.length - 1);
    }
    
    private int helper(int a, int[] b, int index) {
        if (index == -1) {
            return 1;
        }
        int lastDigit = b[index];
        return powmod(helper(a, b, index - 1), 10) * powmod(a, lastDigit) % base;
    }
    
    private int powmod(int a, int b) {
        a %= base;
        int result = 1;
        for (int i = 0; i < b; i++) {
            result = (result * a) % base;
        }
        return result;
    }
}
