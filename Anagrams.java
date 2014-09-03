/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
*/
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> resultList = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return resultList;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String newStr = new String(charArr);
            if (map.containsKey(newStr)) {
                List<String> strList = map.get(newStr);
                strList.add(s);
                map.put(newStr, strList);
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(s);
                map.put(newStr, newList);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> strList = entry.getValue();
            if (strList.size() >= 2) {
                resultList.addAll(strList);
            }
        }
        return resultList;
    }
}
