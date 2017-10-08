/*
Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].
*/
class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (String a, String b) -> b.length() - a.length());
        Set<String> duplicates = getDuplicates(strs);
        
        for (int i = 0; i < strs.length; i++) {
            if (!duplicates.contains(strs[i])) {
                if (i == 0) return strs[0].length();
                for (int j = 0; j < i; j++) {
                    if (isSubsequence(strs[j], strs[i])) {
                        break;
                    }
                    if (j == i - 1) {
                        return strs[i].length();
                    }
                }
            }
        }
        return -1;
    }
    
    private boolean isSubsequence(String longStr, String shortStr) {
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < longStr.length() && secondIndex < shortStr.length()) {
            if (longStr.charAt(firstIndex) == shortStr.charAt(secondIndex)) {
                secondIndex++;
            }
            firstIndex++;
        }
        return secondIndex == shortStr.length();
    }
    
    private Set<String> getDuplicates(String[] strs) {
        Set<String> set = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String str : strs) {
            if (set.contains(str)) {
                duplicates.add(str);
            }
            set.add(str);
        }
        return duplicates;
    }
}
