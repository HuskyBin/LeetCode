/*

To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).

Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.

Example 1:

Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
Output: "eeebffff"
Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".
Example 2:

Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
"ec" doesn't starts at index 2 in the original S, so we do nothing.
Notes:

0 <= indexes.length = sources.length = targets.length <= 100
0 < indexes[i] < S.length <= 1000
All characters in given inputs are lowercase letters.

*/
class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if (S == null || indexes == null || sources == null || targets == null || indexes.length != sources.length) {
            return "";      
        }
        Map<Integer, String> sourceMap = new HashMap<>();
        Map<Integer, String> targetMap = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            sourceMap.put(indexes[i], sources[i]);
            targetMap.put(indexes[i], targets[i]);
        }
        Arrays.sort(indexes);

        StringBuilder sb = new StringBuilder();
        int start = 0;
        int replaceIndex = 0;
        while (start < S.length() && replaceIndex < indexes.length) {
            int index = indexes[replaceIndex];
            if (start < index) {
                sb.append(S.substring(start, index));
                start = index;
            }
            if (isSameStr(S.substring(index, index + sourceMap.get(index).length()), sourceMap.get(index))) {
                sb.append(targetMap.get(index));
                start += sourceMap.get(index).length();
            } else {
                if (start < S.length()) {
                    sb.append(S.charAt(start));
                }
                start++;
            }
            replaceIndex++;
        }  
        while (start < S.length()) {
            sb.append(S.charAt(start++));
        }
        return sb.toString();
    }

    private boolean isSameStr(String firstStr, String secondStr) {
        if (firstStr.length() != secondStr.length()) {
            return false;
        }
        for (int i = 0; i < firstStr.length(); i++) {
            if (firstStr.charAt(i) != secondStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }


}
