/*
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]
Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
*/
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[nums.length];
        
        int index = a < 0 ? 0 : n - 1;
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            int start = cal(a, b, c, nums[i]);
            int end = cal(a, b, c, nums[j]);
            if (a >= 0) {
                result[index--] = start >= end ? start : end;
                if (start >= end) {
                    i++;
                }
                else {
                    j--;
                }
            }
            else {
                result[index++] = start >= end ? end : start;
                if (start >= end) {
                    j--;
                }
                else {
                    i++;
                }
            }
        }
        return result;
    }
    
    private int cal(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    } 
}
