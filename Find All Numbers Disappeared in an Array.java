/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        for (int i = 0; i < nums.length; i++) {
            
            int valIndex = nums[i];
            while(nums[i] != i + 1 && nums[valIndex - 1] != valIndex) {
                int temp = nums[i];
                nums[i] = nums[valIndex - 1];
                nums[valIndex - 1] = temp;
                valIndex = nums[i];
            }
            
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
