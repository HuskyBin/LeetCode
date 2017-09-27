/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

*/
class Solution {
    public String nextClosestTime(String time) {
        String[] val = time.split(":");
        Set<Integer> set = new HashSet<>();
        int hour = parse(set, val[0]);
        int min = parse(set, val[1]);
        
        int[] times = new int[] {hour, min};
        next(times);
        while (!contains(times[0], times[1], set)) {
            next(times);
        }
        return valid(times[0]) + ":" + valid(times[1]);
    }
    
    private String valid(int time) {
        if (time >= 0 && time <= 9) {
            return "0" + time;
        }
        return time + "";
    }
    
    private boolean contains(int hour, int min, Set<Integer> set) {
        return set.contains(hour / 10) && set.contains(hour % 10) && set.contains(min / 10) && set.contains(min % 10);
    }
    
    private void next(int[] times) {
        int hour = times[0];
        int min = times[1];
        
        min++;
        if (min == 60) {
            hour++;
            min = 0;
        }
        if (hour == 24) {
            hour = 0;
        }
        times[0] = hour;
        times[1] = min;
    }
    
    private int parse(Set<Integer> set, String timeStr) {
        int time = Integer.valueOf(timeStr);
        set.add(time / 10);
        set.add(time % 10);
        return time;
    }
}
