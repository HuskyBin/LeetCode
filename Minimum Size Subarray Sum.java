/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int startIndex = 0;
        int endIndex = 0;
        int result = nums.length + 1;
        int sum = nums[0];
        while(endIndex < nums.length) {
        	if (sum >= s) {
        		result = Math.min(result, endIndex - startIndex + 1);
        		sum -= nums[startIndex];
        		startIndex++;
        	}
        	else {
        		endIndex++;
        		if (endIndex < nums.length) {
        		    sum += nums[endIndex];    
        		}
        	}
        }
        if (result == nums.length + 1) {
        	return 0;
        }
        return result;
    }
}
