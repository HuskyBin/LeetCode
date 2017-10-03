/*
You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

Note: The given array is guaranteed to contain no element "0".

Can you do it in O(n) time complexity and O(1) space complexity?
*/
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null) {
            return false;
        }   
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && dfs(nums, i, 0) != 0) {
                return true;
            }
        }
        return false;
    }
    
    private int dfs(int[] nums, int index, int count) {
        if (count >= nums.length) {
            return nums[index];
        }
        int newIndex = (index + nums[index] + nums.length) % nums.length;
        if (newIndex == index || nums[newIndex] * nums[index] < 0 || dfs(nums, newIndex, count + 1) == 0) {
            nums[index] = 0;
        }
        return nums[index];
    }
}
// 迭代

class Solution(object):
    def circularArrayLoop(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        size = len(nums)
        next = lambda x : (x + nums[x] + size) % size
        for x in range(size):
            if not nums[x]:
                continue
            y, c = x, 0
            while c < size:
                z = next(y)
                if y == z:
                    nums[y] = 0
                if nums[y] * nums[z] <= 0:
                    break
                y = z
                c += 1
                if y == x:
                    return True
            if c == size:
                return True
            y = x
            while c > 0:
                z = next(y)
                nums[y] = 0
                c -= 1
        return False
