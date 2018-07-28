/*
Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

Note:

An integer point is a point that has integer coordinates. 
A point on the perimeter of a rectangle is included in the space covered by the rectangles. 
ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
length and width of each rectangle does not exceed 2000.
1 <= rects.length <= 100
pick return a point as an array of integer coordinates [p_x, p_y]
pick is called at most 10000 times.
Example 1:

Input: 
["Solution","pick","pick","pick"]
[[[[1,1,5,5]]],[],[],[]]
Output: 
[null,[4,1],[4,1],[3,3]]
Example 2:

Input: 
["Solution","pick","pick","pick","pick","pick"]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
Output: 
[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
class Solution {
    private int[][] rects;
    private int[] prefixArr;
    private int sum;
    public Solution(int[][] rects) {
        // sortedRects = new int[rects.length][rects[0].length];
        // Arrays.sort(rects, (a, b) -> containsIntCount(a) - containsIntCount(b));
        // for (int i = 0; i < rects.length; i++) {
        this.rects = rects;
        // }
        
        prefixArr = new int[rects.length];
        for (int i = 0; i < rects.length; i++) {
            int area = containsIntCount(rects[i]);
            sum += area;
            prefixArr[i] = sum;
        }
    }
    
    private int containsIntCount(int[] rect) {
        int row = rect[2] - rect[0];
        int column = rect[3] - rect[1];
        return (row + 1) * (column + 1);
    }
    
    public int[] pick() {
        int randomWeight = (int)(Math.random() * sum) + 1;
        int randomIndex = findRectIndex(prefixArr, randomWeight);
        int[] rect = rects[randomIndex];
        int width = rect[2] - rect[0] + 1;
        int height = rect[3] - rect[1] + 1;
        int randomRow = (int)(Math.random() * width) + rect[0];
        int randomColumn = (int)(Math.random() * height) + rect[1];
        return new int[] {randomRow, randomColumn};
    }
    
    private int findRectIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int result = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] >= target) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
