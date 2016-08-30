/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
public class WordDistance {
    
    public Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        if (words == null) return;
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
    }

    public int shortest(String word1, String word2) {
        List<Integer> listOne = map.get(word1);
        List<Integer> listTwo = map.get(word2);
        
        int min = Integer.MAX_VALUE;
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
