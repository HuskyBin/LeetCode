/*
A magical string S consists of only '1' and '2' and obeys the following rules:

The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.

The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:

1 22 11 2 1 22 1 22 11 2 11 22 ......

and the occurrences of '1's or '2's in each group are:

1 2	2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.

Given an integer N as input, return the number of '1's in the first N number in the magical string S.

Note: N will not exceed 100,000.

Example 1:
Input: 6
Output: 3
Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.

字符串模拟

令魔法字符串ms = '122'，维护指针p，初始令p = 2

若ms[p] == '1' 则向ms追加1个与ms末尾元素不同的字符

否则，向ms追加2个与ms末尾元素不同的字符
*/
class Solution {
    public int magicalString(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }
        int[] number = new int[n];
        number[0] = 1;
        number[1] = 2;
        number[2] = 2;
        int index = 2;
        int lastIndex = 2;
    
        while (lastIndex + 1 < n) {
            if (number[index] == 1) {
                number[lastIndex + 1] = 3 - number[lastIndex];
                index++;
                lastIndex++;
            }
            else {
                number[lastIndex + 1] = 3 - number[lastIndex];
                if (lastIndex + 2 >= n) break;
                number[lastIndex + 2] = 3 - number[lastIndex];
                index++;
                lastIndex += 2;
            }
        }
        int sum = 0;
        for (int value : number) {
            if (value == 1) {
                sum++;
            }
        }
        return sum;
    }
}
