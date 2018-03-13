/*
Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation: 
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Note: 1 <= n <= 109
*/
/*
下面这种解法其实蛮有意思的，其实长度为k的二进制数字符串没有连续的1的个数是一个斐波那契数列f(k)。比如当k=5时，二进制数的范围是00000-11111，我们可以将其分为两个部分，00000-01111和10000-10111，因为任何大于11000的数字都是不成立的，因为有开头已经有了两个连续1。而我们发现其实00000-01111就是f(4)，而10000-10111就是f(3)，所以f(5) = f(4) + f(3)，这就是一个斐波那契数列啦。那么我们要做的首先就是建立一个这个数组，方便之后直接查值。我们从给定数字的最高位开始遍历，如果某一位是1，后面有k位，就加上f(k)，因为如果我们把当前位变成0，那么后面k位就可以直接从斐波那契数列中取值了。然后标记pre为1，再往下遍历，如果遇到0位，则pre标记为0。如果当前位是1，pre也是1，那么直接返回结果。最后循环退出后我们要加上数字本身这种情况，参见代码如下： 
*/
class Solution {
    public int findIntegers(int num) {
        int[] f = new int[32];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < 32; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        int k = 31;
        int result = 0;
        boolean preIsOne = false;
        while (k >= 0) {
            if ((num & (1 << k)) != 0) {
                result += f[k];
                if (preIsOne) {
                    return result;
                } else {
                    preIsOne = true;
                }
            } else {
                preIsOne = false;
            }
            k--;
        }
        return result + 1;
    }
}
