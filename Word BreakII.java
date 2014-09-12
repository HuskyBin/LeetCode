/*Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

// Should add a cache to cut all impossbial braches.
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> resultList = new ArrayList<>();
        if (s == null || dict == null || s.length() == 0) {
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[s.length()];
        wordBreakCore(s, dict, sb, 0, resultList, dp);
        return resultList;
    }
    
    public boolean wordBreakCore(String s, Set<String> dict, StringBuilder sb, int index, List<String> resultList, int[] dp) {
        if (index == s.length()) {
            String copyStr = sb.toString();
            if (copyStr.length() > 0) {
                copyStr = copyStr.substring(0, copyStr.length() - 1);
            }
            resultList.add(copyStr);
            return true;
        }
        if (dp[index] == -1) {
            return false;
        }
        boolean subResult = false;
        for (int i = index + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(index, i))) {
                sb.append(s.substring(index, i));
                sb.append(' ');
                subResult |= wordBreakCore(s, dict, sb, i, resultList, dp);
                if (subResult == false) {
                    dp[i] = -1;
                }
                sb.setLength(sb.length() - (i - index) - 1);
            }
        }
        if (subResult == false) {
            dp[index] = -1;
        }
        return subResult;
    }
}
