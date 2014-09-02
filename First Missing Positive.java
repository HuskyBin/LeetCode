/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/
// Be carefull about line 35, you should cache the A[A[index] - 1] first, not the A[index]. It is a little tricky. If not,
// it will change the A[index] value, then it will affect the A[A[index] - 1] value. 
public class Solution {
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) {
            return 1;
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] == i + 1) {
                continue;
            }
            swapValue(A, i);
        }
        int result = A.length + 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                result = i + 1;
                break;
            }
        }
        return result;
    }
    
    private void swapValue(int[] A, int index) {
        while (A[index] > 0 && A[index] < A.length + 1 && A[index] != index + 1 && A[A[index] - 1] != A[index]) {
            int temp = A[A[index] - 1];
            A[A[index] - 1] = A[index];
            A[index] = temp;
        }
    }
}
