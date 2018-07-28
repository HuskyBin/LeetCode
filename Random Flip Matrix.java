/*

You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's Math.random() and optimize the time and space complexity.

Note:

1 <= n_rows, n_cols <= 10000
0 <= row.id < n_rows and 0 <= col.id < n_cols
flip will not be called when the matrix has no 0 values left.
the total number of calls to flip and reset will not exceed 1000.
Example 1:

Input: 
["Solution","flip","flip","flip","flip"]
[[2,3],[],[],[],[]]
Output: [null,[0,1],[1,2],[1,0],[1,1]]
Example 2:

Input: 
["Solution","flip","flip","reset","flip"]
[[1,2],[],[],[],[]]
Output: [null,[0,0],[0,1],null,[0,0]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, n_rows and n_cols. flip and reset have no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
class Solution {

    private Map<Integer, Integer> map;
    private int lastIndex;
    private int column;
    private int row;
    public Solution(int n_rows, int n_cols) {
        this.map = new HashMap<>();
        this.lastIndex = n_rows * n_cols - 1;
        this.column = n_cols;
        this.row = n_rows;
    }
    
    public int[] flip() {
        if (lastIndex < 0) {
            throw new IllegalArgumentException ("Can not filp more!");
        }
        int randomIndex = (int)(Math.random() * (lastIndex + 1));
        int lastValue = map.getOrDefault(lastIndex, lastIndex);
        int randomValue = map.getOrDefault(randomIndex, randomIndex);
        map.put(lastIndex, randomValue);
        map.put(randomIndex, lastValue);
        lastIndex--;
        return new int[]{randomValue / this.column, randomValue % this.column};
        
    }
    
    public void reset() {
        map.clear();
        this.lastIndex = row * column - 1;
    }
}
