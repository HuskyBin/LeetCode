/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> resultList = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return resultList;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
           public int compare(Interval obj1, Interval obj2) {
               if (obj1.start == obj2.start) {
                   return Integer.compare(obj1.end, obj2.end);
               }
               else {
                   return Integer.compare(obj1.start, obj2.start);
               }
           }
        });
        resultList.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval curInterval = intervals.get(i);
            Interval preInterval = resultList.get(resultList.size() - 1);
            if (curInterval.start <= preInterval.end) {
                preInterval.end = Math.max(curInterval.end, preInterval.end);
            }
            else {
                resultList.add(curInterval);
            }
        }
        return resultList;
    }
}
