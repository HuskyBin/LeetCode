/*
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
*/
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> resultList = new ArrayList<>();
        if (words == null) {
            return resultList;
        }
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        
        for (String word : words) {
            dfsCore(word, 0, resultList, set, 0);
        }
        return resultList;
    }
    
    private boolean dfsCore(String word, int index, List<String> resultList, Set<String> wordSet, int matchCount) {
        if (index == word.length()) {
            if (matchCount > 1) {
                resultList.add(word);
                return true;
            }
            return false;
        }
        
        for (int end = index + 1; end <= word.length(); end++) {
            String subWord = word.substring(index, end);
            if (wordSet.contains(subWord)) {
                boolean isValid = dfsCore(word, end, resultList, wordSet, matchCount + 1);
                if (isValid) {
                    return true;
                }
            }
        }
        return false;
    }
}
