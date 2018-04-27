/*
Given an array of unique integers, each integer is strictly greater than 1.

We make a binary tree using these integers and each number may be used for any number of times.

Each non-leaf node's value should be equal to the product of the values of it's children.

How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.

Example 1:

Input: A = [2, 4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]
Example 2:

Input: A = [2, 4, 5, 10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 

Note:

1 <= A.length <= 1000.
2 <= A[i] <= 10 ^ 9.
*/
class Solution {
      public int numFactoredBinaryTrees(int[] A) {
        if (A == null) {
            return 0;
        }
        Arrays.sort(A);
        long[] dp = new long[A.length];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }
        long result = 1;
        int mod = 1000000007;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] % A[j] == 0 && map.containsKey(A[i] / A[j])) {
                    dp[i] += dp[j] * dp[map.get(A[i] / A[j])];    
                    dp[i] %= mod;
                }
            }
            result += dp[i];
            result %= mod;
         }
         return (int)result;
    }
}
