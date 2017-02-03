/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/
public class Solution {
    public Map<Character, String> map = new HashMap<>();
    public Set<String> set = new HashSet<>();
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.isEmpty()) return str.isEmpty();
        char curPatten = pattern.charAt(0);
        if (map.containsKey(curPatten)) {
            String value = map.get(curPatten);
            if (value.length() > str.length() || !str.substring(0, value.length()).equals(value)) {
                return false;
            }
            if (wordPatternMatch(pattern.substring(1), str.substring(value.length()))) return true;
        }
        else {
            for (int i = 1; i <= str.length(); i++) {
                if (set.contains(str.substring(0, i))) continue;
                map.put(curPatten, str.substring(0, i));
                set.add(str.substring(0, i));
                if (wordPatternMatch(pattern.substring(1), str.substring(i))) return true;
                map.remove(curPatten);
                set.remove(str.substring(0, i));
            }
        }
        return false;
    }
}
