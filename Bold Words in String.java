/*
Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.

*/
class Solution {
    public String boldWords(String[] words, String S) {
        if (words == null || S == null || S.length() == 0) {
            return S;
        } 
        
        boolean[] bold = new boolean[S.length()];
        int endIndex = 0;
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < words.length; j++) {
                int wordLen = words[j].length();
                if (i + wordLen <= S.length() && S.substring(i, i + wordLen).equals(words[j])) {
                    endIndex = Math.max(endIndex, i + wordLen);
                }
            }
            bold[i] = (endIndex > i) ? true : false;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (bold[i] == false) {
                sb.append(S.charAt(i));
            } else {
                int boldIndex = i;
                while (boldIndex < S.length() && bold[boldIndex]) {
                    boldIndex++;
                }
                sb.append("<b>");
                sb.append(S.substring(i, boldIndex));
                sb.append("</b>");
                i = boldIndex - 1;
            }
        }
    
        return sb.toString();
        
    }
}
