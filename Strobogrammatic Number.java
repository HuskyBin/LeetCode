/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            return false;
        }
        int start = 0;
        int end = num.length() - 1;
        while (start <= end) {
            if(!isCharStrobogram(num.charAt(start), num.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    private boolean isCharStrobogram(char first, char second) {
        if (first == '6') {
            return (second == '9');
        }
        else if (first == '9') {
            return (second == '6');
        }
        else if (first == '1') {
            return (second == '1');
        }
        else if (first == '8') {
            return (second == '8');
        }
        else if (first == '0') {
            return (second == '0');
        }
        else {
            return false;
        }
    }
}
