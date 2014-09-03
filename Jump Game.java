/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/
// greedy algorithm
public class Solution {
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }
        int startIndex = 0;
        while (startIndex < A.length) {
            if (A[startIndex] + startIndex >= A.length - 1) {
                return true;
            }
            if (A[startIndex] == 0) {
                return false;
            }
            int maxIndex = startIndex;
            int maxValue = Integer.MIN_VALUE;
            for (int i = startIndex + 1; i <= A[startIndex] + startIndex; i++) {
                if (A[i] + i >= maxValue) {
                    maxValue = A[i] + i;
                    maxIndex = i;
                }
            }
            startIndex = maxIndex;
        }
        return true;
    }
}
