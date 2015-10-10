/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
*/

// Carefull when substract , it could be overflow

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int sum = (C - A) * (D - B) + (G - E) * (H - F);
        
        return sum - (int)(Math.max(0L, Long.valueOf(Math.min(C, G)) - Long.valueOf(Math.max(A, E))) * Math.max(0L, Long.valueOf(Math.min(D, H)) - Long.valueOf(Math.max(B, F))));
    }
}
