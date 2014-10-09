/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/
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


//Second Solution (by add a occurrence number to indicate how many same numbers apperaed)
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int newIndex = 1;
        int occurrence = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                if (occurrence <= 1) {
                    A[newIndex] = A[i];
                    newIndex++;
            
                }
                occurrence++;
            }
            else {
                A[newIndex] = A[i];
                newIndex++;
                occurrence = 1;
            }
        }
        return newIndex;
    }
}


// Solution 3 use two variable to store the last one and last two number;
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length < 2) {
            return A.length;
        }
        int index = 2;
        int i = 2;
        int lastOne = A[1];
        int lastTwo = A[0];
        while (i < A.length) {
            if (A[i] != lastOne) {
                lastTwo = lastOne;
                lastOne = A[i];
                A[index] = A[i];
                index++;
            }
            else if (A[i] != lastTwo) {
                lastTwo = lastOne;
                lastOne = A[i];
                A[index] = A[i];
                index++;
            }
            else {
                lastTwo = lastOne;
                lastOne = A[i];
            }
            i++;
        }
        return index;
    }
}
