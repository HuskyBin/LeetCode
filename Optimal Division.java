/*
Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.
*/
在不添加任何括号的情况下：

a / b / c / d / ... = a / (b * c * d * ...)
在算式中添加括号会使得被除数和除数的构成发生变化

但无论括号的位置如何，a一定是被除数的一部分，b一定是除数的一部分

原式添加括号方案的最大值，等价于求除数的最小值

因此最优添加括号方案为：

a / (b / c / d / ...) = a * c * d * ... / b
class Solution {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if (nums.length == 2) {
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        sb.append("/(");
        for (int i = 1; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i + 1 < nums.length) {
                sb.append("/");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
