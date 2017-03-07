/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
*/
public class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                curSum += -1;
            }
            else {
                curSum += 1;
            }
            if (map.containsKey(curSum)) {
                if (i - map.get(curSum) > 1) {
                    if (max < (i - map.get(curSum))) {
                        max = i - map.get(curSum);
                    }
                }
            }
            else {
                map.put(curSum, i);
            }
        }
        return max;
    }
}
