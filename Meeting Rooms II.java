/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] startArr = new int[intervals.length];
        int[] endArr = new int[intervals.length];
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        for (int i = 0; i< intervals.length; i++) {
            startArr[i] = intervals[i].start;
        }
        Arrays.sort(intervals, (a, b) -> (a.end - b.end));
        for (int i = 0; i < intervals.length; i++) {
            endArr[i] = intervals[i].end;
        }
        int result = 0;
        int start = 0;
        int end = 0;
        int max = 0;
        while (start < intervals.length && end < intervals.length) {
            if (startArr[start] < endArr[end]) {
                result++;
                max = Math.max(max, result);
                start++;
            }
            else if (startArr[start] > endArr[end]) {
                result--;
                end++;
            }
            else {
                start++;
                end++;
            }
        }
        return max;
        
    }
}

// Second Solution Useing heap
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
       
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int room = 0;
        for (Interval interval : intervals) {
            if (heap.size() == 0) {
                heap.add(interval.end);
                room++;
                continue;
            }
            if (heap.peek() > interval.start) {
                room++;
                heap.add(interval.end);
            }
            else {
                heap.poll();
                heap.add(interval.end);
            }
        }
        return room;
    }
}
