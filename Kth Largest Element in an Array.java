/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }       
        int result = findKthLargestCore(nums, 0, nums.length - 1, nums.length - k + 1);
        return result;
    }

    private int findKthLargestCore(int[] nums, int start, int end, int k) {
        int left = start;
        int right = end;
        int pivot = nums[end];

        while (left != right) {
            while (nums[left] < pivot && left < right) {
                left++;
            }
            while (nums[right] >= pivot && left < right) {
                right--;
            }

            swap(nums, left, right);
        }
        swap(nums, left, end);

        if (k == left + 1) {
            return pivot;
        }
        else if (k < left + 1) {
            return findKthLargestCore(nums, start, left - 1, k);
        }
        else {
            return findKthLargestCore(nums, left + 1, end, k);
        }
    }

    public void swap(int[] nums, int aIndex, int bIndex) {
        int temp = nums[aIndex];
        nums[aIndex] = nums[bIndex];
        nums[bIndex] = temp;
    }
}
