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
        if (s.length() % 2 == 0) {
            return checkCore(s, true);
        }
        else {
            return checkCore(s, false);
        }
    }
    
    private boolean checkCore(String s, boolean isEven) {
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
        if (isEven && set.size() == 0) {
            return true;
        }
        if (isEven == false && set.size() == 1) {
            return true;
        }
        return false;
    }
}
