public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        boolean result = isScrambleCore(s1, s2, 0, s1.length() - 1, 0, s2.length() - 1);
        return result;
    }
    
    public boolean isScrambleCore(String s1, String s2, int s1Start, int s1End, int s2Start, int s2End) {
        if (s1Start == s1End && s2Start == s2End) {
            if (s1.charAt(s1Start) == s2.charAt(s2Start)) {
                return true;
            }
            else {
                return false;
            }
        }
        
        if (!sortedStr(s1.substring(s1Start, s1End + 1)).equals(sortedStr(s2.substring(s2Start, s2End + 1)))) {
            return false;
        }
        
        for (int i = s1Start; i <= s1End - 1; i++) {
            boolean subResult = isScrambleCore(s1, s2, s1Start, i, s2Start, s2Start + (i - s1Start)) &&
                                isScrambleCore(s1, s2, i + 1, s1End, s2Start + (i - s1Start) + 1, s2End);
            if (subResult == true) {
                return true;
            }
            subResult = isScrambleCore(s1, s2, s1Start, i, s2End - (i - s1Start), s2End) &&
                        isScrambleCore(s1, s2, i + 1, s1End, s2Start, s2End - (i - s1Start) - 1);
            if (subResult == true) {
                return true;
            }
        }
        return false;
    }
    
    public String sortedStr(String str) {
        char[] strArr = str.toCharArray();
        Arrays.sort(strArr);
        return String.valueOf(strArr);
    }
}



// version 2 using a Map to cache results
public class Solution {
    
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }   
        
        Map<String, Boolean> map = new HashMap<>();
        boolean result = isScrambleCore(s1, s2, map);
        return result;
    }

    private boolean isScrambleCore(String s1, String s2, Map<String, Boolean> map) {
        if (s1.length() == 1 && s2.length() == 1 && !s1.equals(s2)) {
            return false;
        }
        if (map.containsKey(s1 + "," + s2)) {
            return map.get(s1 + "," + s2);
        }
        if (s1.equals(s2)) {
            return true;
        }

        for (int i = 0; i < s1.length() - 1; i++) {
            boolean subResult = (isScrambleCore(s1.substring(0, i + 1), s2.substring(0, i + 1), map) && 
                                isScrambleCore(s1.substring(i + 1, s1.length()), s2.substring(i + 1, s2.length()), map)) ||
                                (isScrambleCore(s1.substring(0, i + 1), s2.substring(s2.length() - i - 1, s2.length()), map) &&
                                isScrambleCore(s1.substring(i + 1, s1.length()), s2.substring(0, s2.length() - i - 1), map));
            if (subResult == true) {
                map.put(s1 + "," + s2, true);
                return true;
            }
        }
        map.put(s1 + "," + s2, false);
        return false;
    }
}
