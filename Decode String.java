/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/
public class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < s.length()) {
            char curChar = s.charAt(index);
            StringBuilder num = new StringBuilder();
            if (curChar >= '1' && curChar <= '9') {
                while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                    num.append(s.charAt(index));
                    index++;
                }
                
                int count = 1;
                int fastIndex = index + 1;
                while (fastIndex < s.length() && count != 0) {
                    if (s.charAt(fastIndex) == '[') {
                        count++;
                    }
                    else if (s.charAt(fastIndex) == ']') {
                        count--;
                    }
                    fastIndex++;
                }
                String subString = decodeString(s.substring(index + 1, fastIndex - 1));
                int times = Integer.valueOf(num.toString());
                while (times > 0) {
                    sb.append(subString);
                    times--;
                }
                index = fastIndex - 1;
            }
            else {
                sb.append(s.charAt(index));
            }
            index++;
        }
        return sb.toString();
    }
}
