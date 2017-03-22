/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 0);
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            int looking = sum - k;
            if (map.containsKey(looking)) {
                max = Math.max(max, i - map.get(looking));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        if (max == Integer.MIN_VALUE) {
            return 0;
        }
        return max;
    }
}
