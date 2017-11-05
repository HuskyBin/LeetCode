/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
Credits:
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String st2Reverse = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(st2Reverse) && map.get(st2Reverse) != i) {
                        List<Integer> newList = Arrays.asList(map.get(st2Reverse), i);
                        result.add(newList);
                    }
                }
                if (isPalindrome(str2)) {
                    String st1Reverse = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(st1Reverse) && map.get(st1Reverse) != i && str2.length() > 0) {
                        List<Integer> newList = Arrays.asList(i, map.get(st1Reverse));
                        result.add(newList);
                    }
                }
            }
        }
        return result;
    }
    
    private boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
