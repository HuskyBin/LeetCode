/*
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> indexMap = new HashMap<>();
        int maxValue = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < s.length()) {
            while (endIndex < s.length() && !indexMap.containsKey(s.charAt(endIndex))) {
                indexMap.put(s.charAt(endIndex), endIndex);
                endIndex++;
            }
            maxValue = Math.max(maxValue, endIndex - startIndex);
            if (endIndex >= s.length()) {
                break;
            }
            int preIndex = indexMap.get(s.charAt(endIndex));
            while (startIndex <= preIndex) {
                indexMap.remove(s.charAt(startIndex));
                startIndex++;
            }
            indexMap.put(s.charAt(endIndex), endIndex);
            endIndex++;
        }
        return maxValue;
    }
}
