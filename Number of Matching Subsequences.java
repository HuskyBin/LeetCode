/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
*/
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new LinkedList<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).addLast(word);
        }
        
        int result = 0;
        for (int i = 0; i < S.length(); i++) {
            char curChar = S.charAt(i);
            Deque<String> queue = map.get(curChar);
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                  String curWord = queue.removeFirst();
                if (curWord.length() == 1) {
                    result++;
                } else {
                    map.get(curWord.charAt(1)).addLast(curWord.substring(1));
                }
            }
        }
        return result;
    }
}
