／*
An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

Example 1:
Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
Output: 3
Explanation:
Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
Also, there isn't a smaller size set that fulfills the above condition.
Thus, we output the size of this set, which is 3.
Example 2:
Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
Output: 5
Explanation:
An example of a minimum sized set is {1, 2, 3, 4, 5}.
Note:

intervals will have length in range [1, 3000].
intervals[i] will have length 2, representing some integer interval.
intervals[i][j] will be an integer in [0, 10^8].

*／

class Solution {
    // 贪心原则，按end 排序，如果需要加入新的数字，尽量加右端的数字
    // 然后分情况， 是加一个数，还是两个数，
    // 加一个数的时候，讨论情况看 max 和 secondmax如何变
    // 加两个数的时候，直接加最大的两个数
    public int intersectionSizeTwo(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }
        // secondMax 是在最后S里面倒数第二大的数
        int secondMax = -2;
        //  max 是在最后S中最大的数
        int max = -1;
        int size = 0;
        Arrays.sort(intervals, (a, b) -> ((a[1] != b[1]) ? a[1] - b[1] : a[0] - b[0]));
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] <= secondMax) {
                continue;
            }
            if (cur[0] <= max) {
                size ++;
                if (cur[1] == max) {
                    secondMax = max - 1;
                } else {
                    secondMax = max;
                    max = cur[1];
                }
            } else {
                size += 2;
                max = cur[1];
                secondMax = max - 1;
            }
        }
        return size;
    }
}
