/*
Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
*/
public class Solution {
    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            return null;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            boolean isFindResult = checkIfFind(haystack, needle, i);
            if (isFindResult == true) {
                return haystack.substring(i, haystack.length());
            }
        }
        return null;
    }
    
    private boolean checkIfFind(String haystack, String needle, int index) {
        for (int i = 0; i < needle.length(); i++) {
            if (haystack.charAt(index + i) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
