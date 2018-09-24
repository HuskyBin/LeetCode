/*
Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

 

Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]
*/
/**
We can use greedy algorithm to solve this problem and the best status each time is bipartite, which means every element from left + K and every element from right - K. Firstly, we need to sort the Array. And, we can assume all the numbers array A - K. So, we can get the initial candidate. Then we increase the number of elements that + K and each status will update min & max and the candidate.
*/
class Solution {
    public int smallestRangeII(int[] A, int K) {
        if (A == null) {
            return 0;
        }   
        int len = A.length;
        Arrays.sort(A);
        int result = A[len - 1] - A[0];
        for (int i = 0; i < A.length - 1; i++) {
            int max = Math.max(A[len - 1] - K, A[i] + K);
            int min = Math.min(A[0] + K, A[i + 1] - K);
            result = Math.min(result, max - min);
        }
        return result;
    }
}
