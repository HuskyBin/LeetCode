/*
Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

 

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
 
*/
class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int start = 0;
        int result = 0;
        while (start < A.length) {
            if (start < A.length - 1 && A[start + 1] <= A[start]) {
                start++;
                continue;
            }
            int end = start + 1;
            while (end < A.length && A[end] > A[end - 1]) {    
                end++;
            }
            if (end == A.length) {
                break;
            }
            int nextEnd = end;
            boolean isFound = false;
            while (nextEnd < A.length && A[nextEnd] < A[nextEnd - 1]) {
                nextEnd++;
                isFound = true;
            }
            if (isFound) {
                result = Math.max(result, nextEnd - start);    
            }
            start = nextEnd - 1;
        }
        return result;
    }
}
