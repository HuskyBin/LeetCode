/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
 

Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

*/
class Solution {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return false;
        }
        Stack<Character> sStack = parseStr(S);
        Stack<Character> tStack = parseStr(T);
        
        while (!sStack.isEmpty() && !tStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) {
                return false;
            }
        }
        return sStack.isEmpty() && tStack.isEmpty();
    }
    
    private Stack<Character> parseStr(String str) {
        Stack<Character> sStack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '#') {
                if (!sStack.isEmpty()) {
                    sStack.pop();
                }
            } else {
                sStack.push(c);
            }
        }
        return sStack;
    }
}
