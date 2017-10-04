/*
We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
Note: The length of the input array will not exceed 20,000.
*/
public class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);

        int result = 0;
        int left = 0, right = 0;

        while (right < nums.length) {
            if (nums[right] - nums[left] < 1)
                right++;
            else if (nums[right] - nums[left] == 1) {
                result = Math.max(result, right - left + 1);
                right++;
            } else
                left++;
        }

        return result;
    }
}
