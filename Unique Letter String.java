/*
A character is unique in string S if it occurs exactly once in it.

For example, in string S = "LETTER", the only unique characters are "L" and "R".

Let's define UNIQ(S) as the number of unique characters in string S.

For example, UNIQ("LETTER") =  2.

Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.

If there are two or more equal substrings at different positions in S, we consider them different.

Since the answer can be very large, retrun the answer modulo 10 ^ 9 + 7.

 

Example 1:

Input: "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: "ABA"
Output: 8
Explanation: The same as example 1, except uni("ABA") = 1.
 

Note: 0 <= S.length <= 10000.
*/
class Solution {
    public int uniqueLetterString(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char curChar = S.charAt(i);
            map.computeIfAbsent(curChar, key -> new ArrayList<Integer>()).add(i);
        }
        long ans = 0;
        for (List<Integer> index : map.values()) {
            for (int i = 0; i < index.size(); i++) {
                int preIndex = (i > 0) ? index.get(i - 1) : -1;
                int curIndex = index.get(i);
                int nextIndex = (i < index.size() - 1) ? index.get(i + 1) : S.length();
                ans += (curIndex - preIndex) * (nextIndex - curIndex);
                ans %= 1000000007;
            }
        }
        return (int)ans;
    }
}
