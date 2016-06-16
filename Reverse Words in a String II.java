/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array
*/
public class Solution {
    public void reverseWords(char[] s) {
        if (s == null) {
            return;
        }
        
        reverse(s, 0, s.length - 1);
        int start = 0;
        int end = 0;
        while (end < s.length) {
            while (end < s.length && s[end] != ' ') {
                end++;
            }
            if (end < s.length && s[end] == ' ') {
                reverse(s, start, end - 1);
            }
            while (end < s.length && s[end] == ' ') {
                end++;
            }
            if (end < s.length) {
                start = end;
            }
        }
        reverse(s, start, end - 1);
    }
    
    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }
}
