/*
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.

*/

// 就是找规律，你会发现，所有是平方数的数字最后的灯都是亮的，其他的都是暗的
public class Solution {
    public int bulbSwitch(int n) {
        int result = 0;
        for (int i = 1; i * i <= n; i++) {
            result++;
        }
        return result;
    }
}

//Method 2  直接qu平方根
public class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}

