/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

// The key is when a current range is sorted 1 2 3, then A[start] <= A[end],
// In this case, we could just return A[start] directly!
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return Integer.MAX_VALUE;
        }
        int start = 0;
        int end = num.length - 1;
        while (start <= end) {
            if (num[start] <= num[end]) {
                return num[start];
            }
            int middle = start + (end - start) / 2;
            if (middle > 0 && num[middle] < num[middle - 1]) {
                return num[middle];
            }
            else if (num[middle] >= num[start]) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return Integer.MIN_VALUE;
    }
}
