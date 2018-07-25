import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        testOne();
    }
    
    private static void testOne() {
        final List<Interval> listOne = new ArrayList<>();
        listOne.add(new Interval(0, 2));
        listOne.add(new Interval(5, 10));
        listOne.add(new Interval(13, 23));
        listOne.add(new Interval(24, 25));
        
        final List<Interval> listTwo = new ArrayList<>();
        listTwo.add(new Interval(1, 5));
        listTwo.add(new Interval(8, 12));
        listTwo.add(new Interval(15, 18));
        listTwo.add(new Interval(20, 24));
        listTwo.add(new Interval(200, 240));
        final List<Interval> results = intersection(listOne, listTwo);
        printInterval(results);
    }
    
    private static void printInterval(List<Interval> list) {
        System.out.print("list: ");
        for (Interval interval : list) {
            System.out.print(" " + interval.start + ":" + interval.end + " ");
        }
        System.out.println();
    }
    
    public static List<Interval> intersection(List<Interval> listOne, List<Interval> listTwo) {
        if (listOne == null || listTwo == null) {
            return listOne == null ? listTwo : listOne;
        }
        final List<Interval> results = new ArrayList<>();
        int firstIndex = 0;
        int secondIndex = 0;
        Interval preIterval = null;
        while (firstIndex < listOne.size() && secondIndex < listTwo.size()) {
            final Interval firstInterval = listOne.get(firstIndex);
            final Interval secondInterval = listTwo.get(secondIndex);
            
            if (firstInterval.start > secondInterval.end) {
                if (isIntersectWithLast(results, secondInterval)) {
                    Interval lastInterval = results.get(results.size() - 1);
                    lastInterval.end = Math.max(lastInterval.end, secondInterval.end);
                } else {
                    results.add(secondInterval);
                }
                secondIndex++;
            } else if (secondInterval.start > firstInterval.end) {
                 if (isIntersectWithLast(results, firstInterval)) {
                    Interval lastInterval = results.get(results.size() - 1);
                    lastInterval.end = Math.max(lastInterval.end, firstInterval.end);
                } else {
                    results.add(firstInterval);
                }
                firstIndex++;
            } else {
                final int start = Math.min(firstInterval.start, secondInterval.start);
                final int end = Math.max(firstInterval.end, secondInterval.end);
                Interval newInterval = new Interval(start, end);
                if (isIntersectWithLast(results, newInterval)) {
                    Interval lastInterval = results.get(results.size() - 1);
                    lastInterval.end = Math.max(lastInterval.end, newInterval.end);
                } else {
                    results.add(newInterval);
                }
                firstIndex++;
                secondIndex++;
            }
        }
        int leftIndex = firstIndex < listOne.size() ? firstIndex : secondIndex;
        List<Interval> leftList = firstIndex < listOne.size() ? listOne : listTwo;
        while (firstIndex < leftList.size()) {
            results.add(leftList.get(firstIndex++));
        }
        return results;
    }
    
    private static boolean isIntersectWithLast(List<Interval> list, Interval interval) {
        if (list.size() == 0) {
            return false;
        }
        Interval lastInterval = list.get(list.size() - 1);
        return lastInterval.end >= interval.start;
    }
    

    
    public static class Interval {
        public int start;
        public int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }    
}

