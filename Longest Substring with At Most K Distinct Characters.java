/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.


*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int i = 0;
        int max = 0;
        int num = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)] == 0) {
                num++;
            }
            count[s.charAt(j)]++;
            while (num > k) {
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0) {
                    num--;
                }
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
