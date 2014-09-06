/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.


*/

//双指针，动态维护一个区间。尾指针不断往后扫，当扫到有一个窗口包含了所有T的字符后，然后再收缩头指针，直到不能再收缩为止。最后记录所有可能的情况中窗口最小的

public class Solution {
    public String minWindow(String S, String T) {
        if (S == null || S == null) {
            return null;
        }
        if (S.length() < T.length()) {
            return "";
        }
        Map<Character, Integer> expectMap = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            if (expectMap.containsKey(T.charAt(i))) {
                int num = expectMap.get(T.charAt(i));
                expectMap.put(T.charAt(i), num + 1);
            }
            else {
                expectMap.put(T.charAt(i), 1);
            }
        }
        
        Map<Character, Integer> appearMap = new HashMap<>();
        int appeared = 0;
        int winStart = 0;
        int winEnd = 0;
        int minStart = 0;
        int min = Integer.MAX_VALUE;
        for (winEnd = 0; winEnd < S.length(); winEnd++) {
            if (expectMap.containsKey(S.charAt(winEnd))) {
                if (appearMap.containsKey(S.charAt(winEnd))) {
                    int num = appearMap.get(S.charAt(winEnd));
                    appearMap.put(S.charAt(winEnd), num + 1);
                }
                else {
                    appearMap.put(S.charAt(winEnd), 1);
                }
                if (appearMap.get(S.charAt(winEnd)) <= expectMap.get(S.charAt(winEnd))) {
                    appeared++;
                }
            }
            if (appeared == T.length()) {
                while (!expectMap.containsKey(S.charAt(winStart)) || appearMap.get(S.charAt(winStart)) > expectMap.get(S.charAt(winStart))) {
                    if (appearMap.containsKey(S.charAt(winStart))) {
                        appearMap.put(S.charAt(winStart), appearMap.get(S.charAt(winStart)) - 1);   
                    }
                    winStart++;
                }
                if (min > (winEnd - winStart + 1)) {
                    min = winEnd - winStart + 1;
                    minStart = winStart;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return "";
        }
        return S.substring(minStart, minStart + min);
    }
}
