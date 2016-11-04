/*
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
*/
public class Solution {
    public int arrangeCoins(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int start = 1;
        int end = n;
        long target = (long)n * 2;
        while (start <= end) {
            int middle = start + (end - start) / 2;
 
            long middleValue = (long)middle * (middle + 1);
            if (middleValue == target) {
                return middle;
            }
            else if (middleValue < target) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return end;
    }
}
