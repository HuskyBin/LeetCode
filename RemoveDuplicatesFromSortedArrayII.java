public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int newIndex = 0;
        int fastIndex = 0;
        while (fastIndex < A.length) {
            if (fastIndex < 2 || A[fastIndex] != A[fastIndex - 1]) {
                A[newIndex] = A[fastIndex];
                newIndex++;
                if (fastIndex < A.length - 1 && A[fastIndex] == A[fastIndex + 1]) {
                    fastIndex++;
                    A[newIndex] = A[fastIndex];
                    newIndex++;
                }
            }
            fastIndex++;
        }
        return newIndex;
    }
}
