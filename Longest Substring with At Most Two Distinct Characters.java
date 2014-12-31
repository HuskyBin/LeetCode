/*
找到最多含有两个不同字符的子串的最长长度。例如：eoeabc，最长的是eoe为3，其他都为2.
*/
import java.util.*;
public class LongestSubstring {
    public static void main(String[] args) {
        LongestSubstring obj = new LongestSubstring();
        String result =obj.findLongestSubstring("ceeeeec");
        System.out.println(result);
    }

    public String findLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 1;
        map.put(s.charAt(0), 1);
        int max = 0;
        String result = null;
        while (end < s.length()) {
            while (end < s.length() && map.size() <= 2) {
                char curChar = s.charAt(end);
                if (map.containsKey(curChar)) {
                    map.put(curChar, map.get(curChar) + 1);
                }
                else {
                    map.put(curChar, 1);
                }
                end++;
            }
            if (end == s.length() && map.size() <= 2) {
                if (end - start > max) {
                    max = end - start;
                    result = s.substring(start, end);
                }
                break;
            }
            else {
                 if (end - 1 - start > max) {
                    max = end - 1 - start;
                    result = s.substring(start, end - 1);
                }
            }
            while (start < end && map.size() > 2) {
                char curChar = s.charAt(start);
                if (map.containsKey(curChar)) {
                    int count = map.get(curChar);
                    if (count == 1) {
                        map.remove(curChar);
                    }
                    else {
                        map.put(curChar, count - 1);
                    }
                }
                start++;
            }
        }
        return result;
    }
}
