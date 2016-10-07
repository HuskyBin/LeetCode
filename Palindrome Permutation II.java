/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].
*/
public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> resultList = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return resultList;
        }
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0 ; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (set.contains(curChar)) {
                set.remove(curChar);
            }
            else {
                set.add(curChar);
            }
            if (!map.containsKey(curChar)) {
                map.put(curChar, 0);
            }
            map.put(curChar, map.get(curChar) + 1);
        }
        if (set.size() > 1) {
            return resultList;
        }
        Character c = null;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                c = s.charAt(i);
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        generateCore(sb, map, resultList, c, s.length());
        return resultList;
    }
    
    
    private void generateCore(StringBuilder sb, Map<Character, Integer> map, List<String> resultList, Character c, int len) {
        if (2 * sb.length() == (len - (c == null ? 0 : 1))) {
            if (c != null) {
                StringBuilder copySb = new StringBuilder(sb.toString());
                String str = sb.toString() + c + copySb.reverse().toString();
                resultList.add(str);
            }
            else {
                StringBuilder copySb = new StringBuilder(sb.toString());
                String str = sb.toString() + copySb.reverse().toString();
                resultList.add(str);
            }
            return;
        }
        for (Character ch : map.keySet()) {
            if (map.get(ch) > 1) {
                map.put(ch, map.get(ch) - 2);
                sb.append(ch);
                generateCore(sb, map, resultList, c, len);
                sb.setLength(sb.length() - 1);
                map.put(ch, map.get(ch) + 2);
            }
        }
    }
}
