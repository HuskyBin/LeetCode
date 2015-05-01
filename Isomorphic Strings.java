/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

Show Tags

*/
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> tToSmap = new HashMap<>();
        Map<Character, Character> sToTMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (tToSmap.containsKey(tChar)) {
                if (tToSmap.get(tChar) != sChar) {
                    return false;
                }
            }
            else {
                tToSmap.put(tChar, sChar);
            }
            if (sToTMap.containsKey(sChar)) {
                if (sToTMap.get(sChar) != tChar) {
                    return false;
                }
            }
            else {
                sToTMap.put(sChar, tChar);
            }
        }
        return true;
    }
}
