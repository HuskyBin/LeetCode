/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() == 0 || dict.size() == 0) {
            return 0;
        }
        if (start.equals(end)) {
            return 1;
        }
        Queue<String> queue = new LinkedList<>();
        Map<String, Boolean> processedMap = new HashMap<>();
        queue.offer(start);
        processedMap.put(start, true);
        int result = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int curSize = 0; curSize < size; curSize++) {
                String topString = queue.poll();
                for (int i = 0; i < topString.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newString = topString.substring(0,i) + c + topString.substring(i + 1, topString.length());
                        if (newString.equals(end)) {
                            return result + 1;
                        }
                        if (dict.contains(newString) && !processedMap.containsKey(newString)) {
                            queue.offer(newString);
                            processedMap.put(newString, true);
                        }
                    }
                }
            }
            result++;
        }
        return 0;
    }
}
