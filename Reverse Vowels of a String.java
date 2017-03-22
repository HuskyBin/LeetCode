/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/
public class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder firstSb = new StringBuilder();
        StringBuilder endSb = new StringBuilder();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (isVowel(startChar) && isVowel(endChar)) {
                firstSb.append(endChar);
                endSb.append(startChar);
                start++;
                end--;
            }
            else if (isVowel(startChar) && !isVowel(endChar)) {
                endSb.append(endChar);
                end--;
            }
            else if (!isVowel(startChar) && isVowel(endChar)) {
                firstSb.append(startChar);
                start++;
            }
            else {
                firstSb.append(startChar);
                endSb.append(endChar);
                start++;
                end--;
            }
        }
        if (start == end) {
            firstSb.append(s.charAt(start));        
        }
    
        return firstSb.toString() + endSb.reverse().toString();
    }
    
    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        }
        return false;
    }
}
