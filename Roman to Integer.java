/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/
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
