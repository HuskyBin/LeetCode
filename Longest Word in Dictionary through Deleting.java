/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/
public class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || d == null) {
            return null;
        }
        PriorityQueue<String> heap = new PriorityQueue<>(1, new Comparator<String>() {
           public int compare(String a, String b) {
               if (a.length() != b.length()) {
                   return b.length() - a.length();
               }
               else {
                   return a.compareTo(b);
               }
           } 
        });
        for (String str : d) {
            heap.add(str);
        }
        while (!heap.isEmpty()) {
            String str = heap.poll();
            if (isResult(s, str)) {
                return str;
            }
        }
        return "";
    }
    
    private boolean isResult(String s, String str) {
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < s.length() && bIndex < str.length()) {
            char aChar = s.charAt(aIndex);
            char bChar = str.charAt(bIndex);
            if (aChar == bChar) {
                aIndex++;
                bIndex++;
            }
            else {
                aIndex++;
            }
        }
        if (bIndex == str.length()) {
            return true;
        }
        else {
            return false;
        }
    }
}
