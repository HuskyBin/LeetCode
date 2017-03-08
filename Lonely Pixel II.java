
/*
Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.
*/
public class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        List<List<Integer>> rowList = new ArrayList<>();
        for (int i = 0; i < picture.length; i++) {
            List<Integer> newRow = new ArrayList<>();
            rowList.add(newRow);
        }
        List<List<Integer>> columnList = new ArrayList<>();
        for (int i = 0; i < picture[0].length; i++) {
            List<Integer> newColumn = new ArrayList<>();
            columnList.add(newColumn);
        }
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    rowList.get(i).add(j);
                    columnList.get(j).add(i);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < picture.length; i++) {
            List<Integer> blackColumn = rowList.get(i);
            if (blackColumn.size() != N) {
                continue;
            }
            for (int column : blackColumn) {
                List<Integer> blackRow = columnList.get(column);
                if (blackRow.size() != blackColumn.size()) {
                    continue;
                }   
                boolean match = true;
                for (int j = 0; j < blackRow.size(); j++) {
                    List<Integer> matchBlackColumn = rowList.get(blackRow.get(j));
                   if (matchBlackColumn.size() != blackColumn.size()) {
                       match = false;
                       break;
                   }
                   for (int k = 0; k < matchBlackColumn.size(); k++) {
                       if (matchBlackColumn.get(k) != blackColumn.get(k)) {
                           match = false;
                           break;
                       }
                   }
                }
                if (match) result++;
            }
            
        }
        return result;
    }
}
