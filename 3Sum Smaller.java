/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
*/
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                result += findValidCompare(nums, j + 1, target - nums[i] - nums[j]);
            }
        }
        return result;
    }
    
    private int findValidCompare(int[] nums, int startIndex, int target) {
        int start = startIndex;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] < target) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return start - startIndex;
    }
}
