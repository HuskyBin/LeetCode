/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return result;
        }
        int[] lastIndex = new int[26];
        char[] charArr = S.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            lastIndex[charArr[i] - 'a'] = i;
        }
        int index = 0;
        while (index < S.length()) {
            char curChar = S.charAt(index);
            int second = index;
            int endIndex = lastIndex[curChar - 'a'];
            while (second != endIndex) {
                if (lastIndex[S.charAt(second) - 'a'] > endIndex) {
                    endIndex = lastIndex[S.charAt(second) - 'a'];
                }
                second++;
            }
            result.add(endIndex - index + 1);
            index = endIndex + 1;
        }
        return result;
    }
}
