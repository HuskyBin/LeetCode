/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/
public class Solution {
    public String getPermutation(int n, int k) {
        if (n <= 0 || k <= 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(i);
        }
        String result = getPermutationCore(n, k, sb);
        return result;
    }
    
    private String getPermutationCore(int n, int k, StringBuilder sb) {
        if (n == 1) {
            return sb.toString();
        }
        StringBuilder resultSb = new StringBuilder();
        int facNum = getFactorial(n - 1);
        int index = (k - 1) / facNum;
        resultSb.append(sb.charAt(index));
        sb.deleteCharAt(index);
        resultSb.append(getPermutationCore(n - 1, k - index * facNum, sb));
        return resultSb.toString();
    }
    
    private int getFactorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
