/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/

public class Solution {
    public String reverseWords(String s) {
        if (s ==null || s.length() == 0) {
            return s;
        }
        String str = reverseStr(s);
        int startIndex = 0;
        while (startIndex < str.length() && str.charAt(startIndex) == ' ') {
            startIndex++;
        }
        
        StringBuilder sb = new StringBuilder();
        while (startIndex < str.length()) {
            int forwardStart = startIndex + 1;
            while (forwardStart < str.length() && str.charAt(forwardStart) != ' ') {
                forwardStart++;
            }
            sb.append(reverseStr(str.substring(startIndex, forwardStart)));
            startIndex = forwardStart;
            sb.append(' ');
            while(startIndex < str.length() && str.charAt(startIndex) == ' ') {
                startIndex++;
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
    
    public String reverseStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
