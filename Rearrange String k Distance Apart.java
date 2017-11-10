/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
s = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
s = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.
*/
class Solution {
    public String rearrangeString(String s, int k) {
        if (s == null || k < 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            map.put(curChar, map.getOrDefault(curChar, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxQueue = new PriorityQueue<>(
            (first, second) -> second.getValue() - first.getValue());
        maxQueue.addAll(map.entrySet());
        Queue<Map.Entry<Character, Integer>> waitingList = new LinkedList<>();
        while (!maxQueue.isEmpty()) {
            Map.Entry<Character, Integer> current = maxQueue.poll();
            result.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitingList.add(current);
            if (waitingList.size() < k) {
                continue;
            }
            Map.Entry<Character, Integer> front = waitingList.poll();
            if (front.getValue() > 0) {
                maxQueue.add(front);
            }
        }
        return result.length() != s.length() ? "" : result.toString();
    }
}
