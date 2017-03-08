/*
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].
*/
public class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture == null) {
            return 0;
        }
        int result = 0;
        boolean[][] visited = new boolean[picture.length][picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B' && visited[i][j] == false) {
                    if (checkValid(picture, i, j, visited)) {
                        result ++;
                    }
                }
            }
        }
        return result;
    }
    
    private boolean checkValid(char[][] picture, int row, int column, boolean[][] visited) {
        boolean result = true;
        for (int i = 0; i < picture[0].length; i++) {
            if (i != column) {
                if (picture[row][i] == 'B') {
                    visited[row][i] = true;
                    result = false;
                }
            }
        }
        for (int i = 0; i < picture.length; i++) {
            if (i != row) {
                if (picture[i][column] == 'B') {
                    visited[i][column] = true;
                    result = false;
                }
            }
        }
        return result;
    }
}
