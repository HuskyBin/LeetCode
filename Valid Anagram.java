/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length()!= t.length()) {
            return false;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (map.containsKey(curChar)){
                map.put(curChar, map.get(curChar) + 1);
            }
            else{
                map.put(curChar, 1);
            }
        }
        
        for (int i = 0; i < t.length(); i++){
            char curChar = t.charAt(i);
            if (map.containsKey(curChar)) {
                int curCount = map.get(curChar);
                if (curCount == 0) {
                    return false;
                }
                else {
                    curCount--;
                    map.put(curChar, curCount);
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
