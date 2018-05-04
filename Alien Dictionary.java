/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/
class Solution {
   public String alienOrder(String[] words) {
    StringBuilder sb = new StringBuilder();
        if (words == null) {
            return sb.toString();
        }
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];
            for (int j = 0; j < firstWord.length() && j < secondWord.length(); j++) {
                if (firstWord.charAt(j) != secondWord.charAt(j)) {
                    if (!map.containsKey(firstWord.charAt(j)) || !map.get(firstWord.charAt(j)).contains(secondWord.charAt(j))) {
                        map.computeIfAbsent(firstWord.charAt(j), key -> new HashSet<Character>()).add(secondWord.charAt(j));
                        degree.put(secondWord.charAt(j), degree.get(secondWord.charAt(j)) + 1);    
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            char curChar = queue.poll();
            sb.append(curChar);
            if (map.containsKey(curChar)) {
                for (char nextChar : map.get(curChar)) {
                    degree.put(nextChar, degree.get(nextChar) - 1);
                    if (degree.get(nextChar) == 0) {
                        queue.add(nextChar);
                    }
                }        
            }
        }
        if (sb.length() != degree.size()) {
            return "";
        }
        return sb.toString();

   }
}
