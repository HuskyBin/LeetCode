/*

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
class Solution {

    private int[] weight;
    private int totalWeight = 0;
    public Solution(int[] w) {
        this.weight = new int[w.length];
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            int weightNumber = w[i];
            sum += weightNumber;
            this.weight[i] = sum;
        }
        this.totalWeight = sum;
    }
    
    public int pickIndex() {
        int random = (int)(Math.random() * totalWeight) + 1;
        int start = 0;
        int end = weight.length - 1;
        int result = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (weight[mid] >= random) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }
}
