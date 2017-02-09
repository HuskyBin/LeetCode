/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        if (nums == null) {
            return resultList;
        }
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                resultList.add(Math.abs(num));
            }
            else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        return resultList;
    }
}
