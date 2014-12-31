/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/
public class Solution {
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int start = 0;
        int end = num.length - 1;
        while (start <= end) {
            if (start == end) {
                return start;
            }
            if (start + 1 == end) {
                if (num[start] < num[end]) {
                    return end;
                }
                else {
                    return start;
                }
            }
            int middle = (start + end) / 2;
            if (middle > 0 && num[middle] > num[middle - 1] && middle < num.length - 1 && num[middle] > num[middle + 1]) {
                return middle;
            }
            else if (middle > 0 && num[middle] < num[middle - 1]) {
                end = middle - 1;
            }
            else if (middle > 0 && num[middle] > num[middle - 1]) {
                start = middle + 1;
            }
            else if (middle < num.length - 1 && num[middle] < num[middle + 1]) {
                start = middle + 1;
            }
            else if (middle < num.length - 1 && num[middle] > num[middle + 1]) {
                end = middle - 1;
            }
            else {
                return -1;
            }
        }
        return -1;
    }
}
