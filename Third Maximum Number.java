/*
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

*/
class Solution {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        
        for (Integer val : nums) {
            if (val.equals(max1) || val.equals(max2) || val.equals(max3)) {
                continue;
            }
            if (max1 == null || val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            }
            else if (max2 == null || val > max2) {
                max3 = max2;
                max2 = val;
            }
            else if (max3 == null || val > max3) {
                max3 = val;
            }
        }
        return max3 == null ? max1 : max3;
    }
}
