/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
Subscribe to see which companies asked this question
*/
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
         if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> result = new HashSet<>();
        Set<Integer> setOne = new HashSet<>();
        for (Integer num : nums1) {
            setOne.add(num);
        }
        for (Integer num : nums2) {
            if (setOne.contains(num)) {
                result.add(num);
            }
        }
        int[] number = new int[result.size()];
        int index = 0;
        for (Integer num : result) {
            number[index++] = num;
        }
        return number;
    }
}
