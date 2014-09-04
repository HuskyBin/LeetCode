/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].


*/
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> resultList = new ArrayList<>();
        if (intervals == null || newInterval == null) {
            return intervals;
        }
        int insertPos = 0;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).end < newInterval.start) {
                resultList.add(intervals.get(i));
                insertPos++;
            }
            else if (newInterval.end < intervals.get(i).start) {
                resultList.add(intervals.get(i));
            }
            else {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        resultList.add(insertPos, newInterval);
        return resultList;
    }
    
    
}
