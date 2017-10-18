/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new LinkedList<>();
        int index = findClosetIndex(arr, x);
        int start = index;
        int end = index + 1;
        while (k > 0) {
            if (start < 0) {
                result.add(arr[end]);
                end++;
            }
            else if (end >= arr.length) {
                result.add(0, arr[start]);
                start--;
            }
            else {
                if (Math.abs(arr[start] - x) <= Math.abs(arr[end] - x)) {
                    result.add(0, arr[start]);
                    start--;
                }
                else {
                    result.add(arr[end]);
                    end++;
                }
            }
            k--;
        }
        return result;
        
    }
    
    private int findClosetIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            else if (arr[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (arr[end] <= target) {
            return end;
        }
        else {
            return start;
        }
    }
}
