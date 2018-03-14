/*
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
*/
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null) {
            return 0;
        }
        int result = 0;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        
        
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> arrList = arrays.get(i);
            int curMax = arrList.get(arrList.size() - 1);
            if (curMax > max1) {
                max2 = max1;
                max1 = curMax;
            } else if (curMax > max2) {
                max2 = curMax;
            }
        }
        
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> arrList = arrays.get(i);
            int curMin = arrList.get(0);
            int curMax = arrList.get(arrList.size() - 1);
            if (curMax == max1) {
                result = Math.max(result, max2 - curMin);
            } else {
                result = Math.max(result, max1 - curMin);
            }
            
        }
        return result;
    }
}
