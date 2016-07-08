/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

*/
public class Solution {
    public boolean isPerfectSquare(int num) {
        if (num <= 0) {
            return false;
        }
        long start = 1;
        long end = num / 2 + 1;
        while (start <= end) {
            long middle = start + (end - start) / 2;
            long middleSquare = middle * middle;
            if (middleSquare == num) {
                return true;
            }
            else if (middleSquare < num) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return false;
    }
}
