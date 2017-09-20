/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

*/
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        final List<Integer> resultList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] index = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(index, -1);
        int maxValue = 1;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && (dp[j] + 1 > dp[i])) {
                    dp[i] = dp[j] + 1;
                    index[i] = j;
                }
            }
            if (maxValue < dp[i]) {
                maxValue = dp[i];
                maxIndex = i;
            }
        }
        for (int i = maxIndex; i != -1; i = index[i]) {
            resultList.add(nums[i]);
        }
        return resultList;
    }
}
