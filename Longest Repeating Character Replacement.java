/*
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
*/
class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        int[] count = new int[26];
        int maxCount = 0;
        int result = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            count[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount <= k) {
                result = Math.max(result, end - start + 1);
            }
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
                maxCount = count[0];
                // 这里不需要更新maxCount， 因为我们是去找最大的substring = maxCount + k， k是不变的，所以，
                ／／ 没必要把maxCount变更小，我们只要当更大当maxCount出现时候，去更新就可以了。
                //for (int i = 1; i < 26; i++) {
                //    maxCount = Math.max(maxCount, count[i]);
                //}
            }
        }
        return result;
    }
}
