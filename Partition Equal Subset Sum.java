/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/

//Dp

public class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int n : nums) {
            for (int i = target; i >= n; i--) {
                dp[i] = dp[i] || dp[i - n];
                if (i == target && dp[i]) return true;
            }
        }
        return false;
    }
}

//DFS
public class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null) {
            return false;
        }
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        Arrays.sort(nums);
        return findTarget(nums, target, 0, 0);
    }
    
    private boolean findTarget(int[] nums, int target, int index, int sum) {
        if (index >= nums.length) {
            return false;
        }
        if (sum + nums[index] == target) {
            return true;
        }
        else if (sum + nums[index] > target) {
            return false;
        }
        else {
            return findTarget(nums, target, index + 1, sum + nums[index]) || findTarget(nums, target, index + 1, sum);
        }
    }
}
