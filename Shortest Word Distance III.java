/*
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || word1 == null || word2 == null) {
            return -1;
        }
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                firstList.add(i);
            }
            if (word.equals(word2)) {
                secondList.add(i);
            }
        }
        int min = words.length;
        if (word1.equals(word2)) {
            for (int i = 1; i < firstList.size(); i++) {
                min = Math.min(min, firstList.get(i) - firstList.get(i - 1));
            }
        }
        else {
            int first = 0;
            int second = 0;
            while (first < firstList.size() && second < secondList.size()) {
                int firstIndex = firstList.get(first);
                int secondIndex = secondList.get(second);
                min = Math.min(min, Math.abs(firstIndex - secondIndex));
                if (firstIndex < secondIndex) {
                    first++;
                }
                else {
                    second++;
                }
            }
        }
        return min;
    }
}
