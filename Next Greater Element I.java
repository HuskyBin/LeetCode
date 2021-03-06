/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null || nums == null) {
            return null;
        }   
        int result[] = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            result[i] = findLarger(findNums[i], nums);
        }
        return result;
    }
    
    private int findLarger(int target, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int j = i + 1;
                while (j < nums.length) {
                    if (nums[j] > target) {
                        return nums[j];
                    }
                    j++;
                }
            }
        }
        return -1;
    }
}
// stack solution
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null || nums == null) {
            return null;
        }   
        int result[] = new int[findNums.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (stack.isEmpty()) {
                stack.push(num);
            }
            else {
                while (!stack.isEmpty() && num > stack.peek()) {
                    map.put(stack.peek(), num);
                    stack.pop();
                }
                stack.push(num);
            }
        }
        for (int i = 0; i < findNums.length; i++) {
            if (map.containsKey(findNums[i])) {
                result[i] = map.get(findNums[i]);
            }
            else {
                result[i] = -1;
            }
            
        }
        return result;
    }
}
