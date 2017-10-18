/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.

*/
class Solution {
    public int findShortestSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (!start.containsKey(nums[i])) {
                start.put(nums[i], i);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            end.put(nums[i], i);
            max = Math.max(max, count.get(nums[i]));
        }
        int result = len;
        for (int num : nums) {
            if (count.get(num) == max) {
                result = Math.min(result, end.get(num) - start.get(num) + 1);
            }
        }
        return result;
    }
}
