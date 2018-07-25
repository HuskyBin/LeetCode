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
        while (firstIndex < listOne.size() && secondIndex < listTwo.size()) {
            final Interval firstInterval = listOne.get(firstIndex);
            final Interval secondInterval = listTwo.get(secondIndex);
            
            if (firstInterval.start > secondInterval.end) {
                secondIndex++;
            } else if (secondInterval.start >= firstInterval.end) {
                firstIndex++;
            } else {
                final int start = Math.max(firstInterval.start, secondInterval.start);
                final int end = Math.min(firstInterval.end, secondInterval.end);
                results.add(new Interval(start, end));
                if (firstInterval.end <= secondInterval.end) {
                    firstIndex++;
                } else {
                    secondIndex++;
                }
            }
        }
        return results;
    }
    

    
    public static class Interval {
        public final int start;
        public final int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }    
}
