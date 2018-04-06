/*
Given two strings S and T, determine if they are both one edit distance apart.
*/
//Better solution
class Solution {
    public boolean isOneEditDistance(String s, String t) {
    	if (s == null || t == null) {
    		return false;
    	}
    	if (Math.abs(s.length() - t.length()) > 1) {
    		return false;
    	}
    	for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
    		if (s.charAt(i) != t.charAt(i)) {
    			if (s.length() == t.length()) {
    				return s.substring(i + 1).equals(t.substring(i + 1));
    			} else if (s.length() < t.length()) {
    				return s.substring(i).equals(t.substring(i + 1));
    			} else {
    				return s.substring(i + 1).equals(t.substring(i));
    			}
    		}
    	}
    	return s.length() != t.length();
    }

}


public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        String small = (s.length() < t.length()) ? s : t;
        String longStr = (s.length() < t.length()) ? t : s;
        boolean isEqualLength = false;
        if (small.length() == longStr.length()) {
            isEqualLength = true;
        }
        if (small.length() == 0 && longStr.length() == 1) {
            return true;
        }
        int first = 0;
        int second = 0;
        boolean hasDiff = false;
        while (first < small.length()) {
            if (small.charAt(first) == longStr.charAt(second)) {
                first++;
                second++;
            }
            else {
                if (hasDiff == false) {
                    hasDiff = true;
                }
                else {
                    return false;
                }
                if (isEqualLength) {
                    first++;
                    second++;
                }
                else {
                    second++;
                }
            }
        }
        if (isEqualLength) return hasDiff;
        if (first == small.length() && second == longStr.length()) {
            return hasDiff;
        }
        else {
            return !hasDiff;
        }
        
    }
}
