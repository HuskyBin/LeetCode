/*
Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:
Input: [1,2,4,-1,2], 2
Output: [1,3,5]
Example 2:
Input: [1,2,4,-1,2], 1
Output: []
Note:
Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].
*/
class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> resultList = new ArrayList<>();
        if (A == null) {
            return resultList;
        }
        int[] dp = new int[A.length];
        int[] path = new int[A.length];
        int[] len = new int[A.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] == -1) {
                continue;
            }
            for (int j = Math.max(0, i - B); j < i; j++) {
                if (A[j] == -1 || dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dp[j] + A[i] < dp[i] || (dp[j] + A[i] == dp[i] && len[i] < len[j] + 1)) {
                    path[i] = j;
                    len[i] = len[j] + 1;
                }
                dp[i] = Math.min(dp[j] + A[i], dp[i]);
            }
        }
        if (dp[A.length - 1] == Integer.MAX_VALUE) {
            return resultList;
        }
        int index = A.length - 1;
        while (index != -1) {
            resultList.add(0, index + 1);
            index = path[index];
        }
        return resultList;
    }
}
