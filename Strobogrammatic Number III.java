/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/
public class Solution {
    public int strobogrammaticInRange(String low, String high) {
        int[] result = new int[1];
        find(low, high, "", result);
        find(low, high, "0", result);
        find(low, high, "1", result);
        find(low, high, "8", result);
        return result[0];
    }
    
    private void find(String low, String high, String str, int[] result) {
        if (low.length() <= str.length() && str.length() <= high.length()) {
            if ((str.length() == low.length() && (Long.valueOf(str).compareTo(Long.valueOf(low)) < 0)) || 
            (str.length() == high.length() && (Long.valueOf(str).compareTo(Long.valueOf(high)) > 0))) {
                return;
            }
            if (!(str.length() > 1 && str.charAt(0) == '0')) {
                result[0]++;
            }     
        }
        if (str.length() + 2 > high.length()) {
            return;
        }
        find(low, high, "0" + str + "0", result);
        find(low, high, "1" + str + "1", result);
        find(low, high, "8" + str + "8", result);
        find(low, high, "6" + str + "9", result);
        find(low, high, "9" + str + "6", result);
    }
}
