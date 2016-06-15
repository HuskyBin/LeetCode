/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int result = 0;
        int count = 0;
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            while (count <= 2 && end < s.length()) {
                char curChar = s.charAt(end);
                if (map.containsKey(curChar) && map.get(curChar) > 0) {
                    map.put(curChar, map.get(curChar) + 1);
                }
                else {
                    if (count == 2) break;
                    count++;
                    map.put(curChar, 1);
                }
                end++;
            }
            result = Math.max(result, (end - start));
            while (start < end) {
                char curChar = s.charAt(start);
                if (map.get(curChar) > 1) {
                    map.put(curChar, map.get(curChar) - 1);
                }
                else {
                    map.put(curChar, 0);
                    count--;
                }
                if (count == 1) {
                    start++;
                    break;
                }
                start++;
            }
        }
        return result;
    }
}
