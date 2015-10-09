/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        
        TreeSet<Long> set = new TreeSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            Long floorValue = set.floor(Long.valueOf(nums[i]));
            Long lowerValue = set.ceiling(Long.valueOf(nums[i]));
            if ((floorValue != null && nums[i] - floorValue <= t) || (lowerValue != null && lowerValue - nums[i] <= t)) {
                return true;
            }
            set.add(Long.valueOf(nums[i]));
            if (i >= k) {
                set.remove(Long.valueOf(nums[i - k]));
            }
        }
        
      return false;
    }
}
