/*
We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input: 
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Note:

L, R  and A[i] will be an integer in the range [0, 10^9].
The length of A will be in the range of [1, 50000].
*/
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null) {
            return 0;
        }
        return count(A, R) - count(A, L - 1);
    }
    
    private int count(int[] A, int bound) {
        int ans = 0;
        int cur = 0;
        for (int num : A) {
            cur = num <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
