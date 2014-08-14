/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        Map<Integer, Boolean> hasIntMap = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            if (!hasIntMap.containsKey(num[i])) {
                hasIntMap.put(num[i], true);
            }
        }
        int maxResult = 0;
        for (int i = 0; i < num.length; i++) {
            if (!hasIntMap.containsKey(num[i]) || hasIntMap.get(num[i]) == false) {
                continue;
            }
            int forwardInt = num[i] + 1;
            while (hasIntMap.containsKey(forwardInt) && hasIntMap.get(forwardInt) == true) {
                hasIntMap.put(forwardInt, false);
                forwardInt++;
            }
            int backInt = num[i] - 1;
            while (hasIntMap.containsKey(backInt) && hasIntMap.get(backInt)  == true) {
                hasIntMap.put(backInt, false);
                backInt--;
            }
            if (forwardInt - backInt - 1 > maxResult) {
                maxResult = forwardInt - backInt - 1;
            }
        }
        return maxResult;
    }
}
