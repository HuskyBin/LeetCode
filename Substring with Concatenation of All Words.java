/*
You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/
public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> resultList = new ArrayList<>();
        if (S == null || L == null) {
            return resultList;
        }
        Map<String, Integer> strMap = new HashMap<>();
        for (String str : L) {
            if (strMap.containsKey(str)) {
                strMap.put(str, strMap.get(str) + 1);
            }
            else {
                strMap.put(str, 1);
            }
        }
        int size = L[0].length();
        int length = size * L.length;
        for (int i = 0; i < S.length() - length + 1; i++) {
            Map<String, Integer> copyMap = new HashMap<>(strMap);
            boolean isFind = checkIfFindResult(S, i, copyMap, size, L.length);
            if (isFind == true) {
                resultList.add(i);
            }
        }
        return resultList;
    }
    
    private boolean checkIfFindResult(String s, int index, Map<String, Integer> copyMap, int size, int number) {
        for (int i = index; i < index + number * size; i += size) {
            String curString = s.substring(i, i + size);
            if (copyMap.containsKey(curString)) {
                if (copyMap.get(curString) <= 0) {
                    return false;
                }
                else {
                    copyMap.put(curString, copyMap.get(curString) - 1);
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
