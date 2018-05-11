/*
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].
*/
class Solution {
   public boolean splitArray(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }       
        int[] prefixSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        } 

        
        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (prefixSum[i - 1] == prefixSum[j - 1] - prefixSum[i]) {
                    set.add(prefixSum[i - 1]);
                }
            }

            for (int k = j + 2; k < nums.length - 1; k++) {
                if ((prefixSum[nums.length - 1] - prefixSum[k] == prefixSum[k - 1] - prefixSum[j]) && set.contains(prefixSum[nums.length - 1] - prefixSum[k])) {
                    return true;
                }
            }
        }
        return false;
    }
}
