/*
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/
public class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        Set<Character> filters = new HashSet<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] < k && count[i] > 0) {
                filters.add((char)('a' + i));
            }
        }
        if (filters.isEmpty()) {
            return s.length();
        }
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < s.length()) {
            if (filters.contains(s.charAt(end))) {
                max = Math.max(max, longestSubstring(s.substring(start, end), k));
                start = end + 1;
            }
            end++;
        }
        max = Math.max(max, longestSubstring(s.substring(start, end), k));
        return max;
    }
}
