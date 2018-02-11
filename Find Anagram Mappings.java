/*
Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

These lists A and B may contain duplicates. If there are multiple answers, output any of them.

For example, given

A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
We should return
[1, 4, 3, 2, 0]
as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
Note:

A, B have equal lengths in range [1, 100].
A[i], B[i] are integers in range [0, 10^5].

*/
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return new int[0];
        }
        int[] result = new int[A.length];
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            indexMap.computeIfAbsent(B[i], k -> new LinkedList<Integer>()).add(i);
        }
        
        for (int i = 0; i < A.length; i++) {
            List<Integer> indexs = indexMap.get(A[i]);
            result[i] = indexs.remove(0);
        }
        return result;
    }
}
