/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/
public class Solution {
    public int sqrt(int x) {
        long startIndex = 1;
        long endIndex = x / 2 + 1;
        while (startIndex <= endIndex) {
            long middle = startIndex + (endIndex - startIndex) / 2;
            if (middle * middle == x) {
                return (int)middle;
            }
            else if (middle * middle < x) {
                startIndex = middle + 1;
            }
            else {
                endIndex = middle - 1;
            }
        }
        return (int)endIndex;
    }
}
