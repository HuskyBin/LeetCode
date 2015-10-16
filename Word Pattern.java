/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> mapTwo = new HashMap<>();

        String[] subStrs = str.split(" ");

        if (subStrs.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < subStrs.length; i++) {
            char curPattern = pattern.charAt(i);
            if (map.containsKey(curPattern)) {
                if (!map.get(curPattern).equals(subStrs[i])) {
                    return false;
                }
            }
            else if (mapTwo.containsKey(subStrs[i])) {
                if (mapTwo.get(subStrs[i]) != curPattern) {
                    return false;
                }
            }
            
            if (!map.containsKey(curPattern)) {
                map.put(curPattern, subStrs[i]);
            }
            if (!mapTwo.containsKey(subStrs[i])) {
                mapTwo.put(subStrs[i], curPattern);
            }
        }

        return true;
    }
}
