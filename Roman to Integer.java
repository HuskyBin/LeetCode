/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

// better
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 || (map.get(c) <= map.get(s.charAt(i - 1)))) {
                result += map.get(c);
            } else {
                result += map.get(c) - 2 * map.get(s.charAt(i - 1));
            }
        }
        return result;
    }
}


public class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<String, Integer> romanMap = new HashMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        romanMap.put("IV", 4);
        romanMap.put("IX", 9);
        romanMap.put("XL", 40);
        romanMap.put("XC", 90);
        romanMap.put("CD", 400);
        romanMap.put("CM", 900);
        
        int result = 0;
        int index = 0;
        while (index < s.length()) {
            if (index < s.length() - 1 && romanMap.containsKey(s.substring(index, index + 2))) {
                result += romanMap.get(s.substring(index, index + 2));
                index += 2;
            }
            else {
                result += romanMap.get(s.substring(index, index + 1));
                index += 1;
            }
        }
        return result;
    }
}
