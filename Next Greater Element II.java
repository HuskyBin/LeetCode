/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.


*/
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return null;
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            int number = nums[i % nums.length];
            while(!stack.isEmpty() && nums[stack.peek()] < number) {
                result[stack.pop()] = number;
            }
            if (i < nums.length) {
                stack.push(i);
            }
        }
        return result;
    }
}
