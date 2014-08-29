/*
Write a function to find the longest common prefix string amongst an array of strings.
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < strs[0].length()) {
            boolean valid = true;
            char c = strs[0].charAt(index);
            for (int i = 0; i < strs.length; i++) {
                if (index >= strs[i].length() || c != strs[i].charAt(index)) {
                    valid = false;
                    break;
                }
            }
            if (valid == true) {
                sb.append(c);
            }
            else {
                break;
            }
            index++;
        }
        return sb.toString();
    }
}
