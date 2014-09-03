/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

*/

// Greedy Algorithm
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0 || A.length == 1) {
            return 0;
        }
        int result = 0;
        int startIndex = 0;
        while (startIndex < A.length) {
            if (startIndex + A[startIndex] >= A.length - 1) {
                return result + 1;
            }
            if (A[startIndex] == 0) {
                return -1;
            }
            int maxIndex = startIndex;
            int maxValue = Integer.MIN_VALUE;
            for (int i = startIndex + 1; i <= A[startIndex] +startIndex; i++) {
                if (A[i] + i >= maxValue) {
                    maxValue = A[i] + i;
                    maxIndex = i;
                }
            }
            result++;
            startIndex = maxIndex;
        }
        return result;
    }
}

// Dp
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] steps = new int[A.length];
        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        if (steps[A.length - 1] == Integer.MAX_VALUE) {
            return -1;
        }
        return steps[A.length - 1];
    }
}
