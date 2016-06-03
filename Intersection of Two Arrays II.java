/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to num2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       if (nums1 == null || nums2 == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> mapOne = new HashMap<>();
        for (Integer num : nums1) {
            if (mapOne.containsKey(num)) {
                mapOne.put(num, mapOne.get(num) + 1);
            }
            else {
                mapOne.put(num, 1);
            }
        }
        for (Integer num : nums2) {
            if (mapOne.containsKey(num) && mapOne.get(num) > 0) {
                result.add(num);
                mapOne.put(num, mapOne.get(num) - 1);
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
