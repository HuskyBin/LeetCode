/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.
*/
public class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return checkCore(s);
    }
    
    private boolean checkCore(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (set.contains(curChar)) {
                set.remove(curChar);
            }
            else {
                set.add(curChar);
            }
        }
        return set.size() <= 1;
    }
}
