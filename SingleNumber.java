public class Solution {
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int result = A[0];
        for (int i = 1; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }
}
