public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        if (A == null || B == null) {
            return;
        }
        int lastIndex = m + n - 1;
        while (n > 0 && m > 0) {
            if (B[n - 1] < A[m - 1]) {
                A[lastIndex] = A[m - 1];
                m--;
                lastIndex--;
            }
            else {
                A[lastIndex] = B[n - 1];
                n--;
                lastIndex--;
            }
        }
        while (n > 0) {
            A[lastIndex] = B[n - 1];
            lastIndex--;
            n--;
        }
    }
}

// Version 2
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        if (A == null || B == null) {
            return;
        }
        int index = m + n - 1;
        m--;
        n--;
        while (m >= 0 || n >= 0) {
            int aValue = (m >= 0) ? A[m] : Integer.MIN_VALUE;
            int bValue = (n >= 0) ? B[n] : Integer.MIN_VALUE;
            if (aValue >= bValue && m >= 0) {
                A[index] = aValue;
                m--;
                index--;
            }
            else {
                A[index] = bValue;
                n--;
                index--;
            }
        }
    }
}
