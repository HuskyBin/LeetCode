/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
// 想着 “bcabc” 这个例子， 就会好写很多，因为当遇到a时候，我们需要把之前bc给扔了。所以需要一个stack
public class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null) {
            return s;
        }
        Map<Character, Integer> count = new HashMap<>();
        Set<Character> passedSet = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (count.containsKey(curChar)) {
                count.put(curChar, count.get(curChar) + 1);
            }
            else {
                count.put(curChar, 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            count.put(curChar, count.get(curChar) - 1);
            if (passedSet.contains(curChar)) {
                continue;
            }
            while (stack.size() > 0 && stack.peek() > curChar && count.get(stack.peek()) > 0) {
                passedSet.remove(stack.pop());
            }
            stack.push(curChar);
            passedSet.add(curChar);
        }
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
