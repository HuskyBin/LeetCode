／*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

*／
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return result;
        }
        
        int count = p.length();
        int start = 0;
        int end = 0;
        // hash 代表需要完成的，大于0表示还有没遇到的，小于0表示多出的
        int[] hash = new int[256];
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        
        while (end < s.length()) {
            
            if (hash[s.charAt(end++)]-- > 0) {
                count--;
            }
            
            if (count == 0) {
                result.add(start);
            }
            
            if (end - start == p.length() && hash[s.charAt(start++)]++ >= 0) {
                count++;
            }
            
        }
        return result;
    }
}
