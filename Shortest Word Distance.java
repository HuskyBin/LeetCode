/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

Show Company Tags
Show Tags
Show Similar Problems

*/
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return -1;
        }
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                List<Integer> curList = map.get(word);
                curList.add(i);
                map.put(word, curList);
            }
            else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                map.put(word, newList);
            }
        }
        
        List<Integer> listOne = map.get(word1);
        List<Integer> listTwo = map.get(word2);
        
        int min = words.length;
        int first = 0;
        int second = 0;
        while (first < listOne.size() && second < listTwo.size()) {
            int firstIndex = listOne.get(first);
            int secondIndex = listTwo.get(second);
            min = Math.min(Math.abs(firstIndex - secondIndex), min);
            if (firstIndex < secondIndex) {
                first++;
            }
            else {
                second++;
            }
        }
        return min;
    }
}
