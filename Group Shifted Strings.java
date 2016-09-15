/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null) {
            return null;
        }
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            StringBuilder sb = new StringBuilder();
            if (str.length() == 0) {
                sb.append("-100");
            }
            else {
                for (int i = 0; i < str.length() - 1; i++) {
                    sb.append(getClosest(str.charAt(i), str.charAt(i + 1)));
                }
            }
            if (map.containsKey(sb.toString())) {
                List<String> curList = map.get(sb.toString());
                curList.add(str);
                map.put(sb.toString(), curList);
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(str);
                map.put(sb.toString(), newList);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
    
    private String getClosest(char a, char b) {
        int result = 0;
        int one = a - b;
        int two = a - b + 26;
        int three = a - b - 26;
        if (Math.abs(one) < Math.abs(two) && (Math.abs(one) < Math.abs(three))) {
            return String.valueOf(one);
        }
        else if (Math.abs(two) < Math.abs(one) && (Math.abs(two) < Math.abs(three))) {
            return String.valueOf(two);
        }
        else if (Math.abs(three) < Math.abs(one) && (Math.abs(three) < Math.abs(one))){
            return String.valueOf(three);
        }
        else {
            return "13";
        }
    }
}

// Clean code

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null) {
            return null;
        }
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            StringBuilder sb = new StringBuilder();
            if (str.length() == 0) {
                sb.append("-100");
            }
            else {
                for (int i = 0; i < str.length() - 1; i++) {
                    sb.append(((str.charAt(i) - str.charAt(i + 1)) + 26) % 26);
                }
            }
            if (map.containsKey(sb.toString())) {
                List<String> curList = map.get(sb.toString());
                curList.add(str);
                map.put(sb.toString(), curList);
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(str);
                map.put(sb.toString(), newList);
            }
        }
        for (String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }
}
