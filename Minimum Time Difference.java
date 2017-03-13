/*
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
*/
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            return 0;
        }
        Collections.sort(timePoints);
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            int diff = caculateDiff(timePoints.get(i - 1), timePoints.get(i));
            minValue = Math.min(minValue, diff);
        }

        int cornerDiff = caculateDiff(timePoints.get(timePoints.size() - 1), "24:00") + caculateDiff("00:00", timePoints.get(0));
        minValue = Math.min(minValue, cornerDiff);
        return minValue;
    }
    
    private int caculateDiff(String firstTime, String secondTime) {
        String[] firstTimeArr = firstTime.split(":");
        String[] secondTimeArr = secondTime.split(":");
        int firstTimeHour = Integer.valueOf(firstTimeArr[0]);
        int firstTimeMin = Integer.valueOf(firstTimeArr[1]);
        int secondTimeHour = Integer.valueOf(secondTimeArr[0]);
        int secondTimeMin = Integer.valueOf(secondTimeArr[1]);
        int result = (secondTimeHour - firstTimeHour) * 60 + (secondTimeMin - firstTimeMin);
        return result;
    }
}
