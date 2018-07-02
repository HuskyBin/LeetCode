/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
*/
class Solution {
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] B = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            B[i + 1] += B[i] + A[i];
        }
        int result = A.length + 1;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < A.length + 1; i++) {
            while (deque.size() > 0 && B[deque.getFirst()] <= B[i] - K) {
                result = Math.min(result, i - deque.getFirst());
                deque.pollFirst();
            }
            while (deque.size() > 0 && B[deque.getLast()] >= B[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return result > A.length ? -1 : result;
    }
}
