/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Show Tags

*/
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int start = 0;
        int end = num.length - 1;
        while (start <= end) {
            if (start == end) {
                return num[start];
            }
            if (num[start] < num[end]) {
                return num[start];
            }
            int middle = start + (end - start) / 2;
            if (middle > 0 && num[middle - 1] > num[middle]) {
                return num[middle];
            }
            if (num[middle] > num[start]) {
                start = middle + 1;
            }
            else if (num[middle] < num[start]) {
                end = middle - 1;
            }
            else {
                start++;
            }
        }
        return Integer.MIN_VALUE;
    }
}
