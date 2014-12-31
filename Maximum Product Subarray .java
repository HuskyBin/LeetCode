/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

Show Tags

*/
public class Solution {
    
    public int maxProduct(int[] A) {
	    if (A == null || A.length == 0) {
	        return -1;
	    }
	    int max = A[0];
	    int min = A[0];
	    int maxResult = A[0];
	    for (int i = 1; i < A.length; i++) {
	        int tempMax = max;
	        int tempMin = min;
	        max = Math.max(Math.max(tempMax * A[i], A[i]), tempMin * A[i]);
	        min = Math.min(Math.min(tempMax * A[i], A[i]), tempMin * A[i]);
	        maxResult = Math.max(maxResult, max);
	    }
	    
	    return maxResult;
    }
}
