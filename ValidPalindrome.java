public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }        
        int startIndex = 0;
        int endIndex = s.length() - 1;
        while (startIndex <= endIndex) {
            char startChar = s.charAt(startIndex);
            char endChar = s.charAt(endIndex);
            if (!isValid(startChar)) {
                startIndex++;
                continue;
            }
            if (!isValid(endChar)) {
                endIndex--;
                continue;
            }
            if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }
    
    public boolean isValid(char curChar) {
        if (curChar >= 'A' && curChar <= 'Z') {
            return true;
        }
        if (curChar >= 'a' && curChar <= 'z') {
            return true;
        }
        if (curChar >= '0' && curChar <= '9') {
            return true;
        }
        return false;
    }
