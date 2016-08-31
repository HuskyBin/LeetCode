/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].*/
public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> resultList = new ArrayList<>();
        List<String> middleCandidates = new ArrayList<>();
        middleCandidates.add("0");
        middleCandidates.add("1");
        middleCandidates.add("8");
        List<String> candidates = new ArrayList<>();
        candidates.add("0");
        candidates.add("1");
        candidates.add("6");
        candidates.add("8");
        candidates.add("9");
        Map<String, String> map = new HashMap<>();
        map.put("0", "0");
        map.put("1", "1");
        map.put("6", "9");
        map.put("8", "8");
        map.put("9", "6");
        
        if (n <= 0) {
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            for (String s : middleCandidates) {
                sb.append(s);
                findCore(sb, resultList, candidates, map, n - 1);
                sb.deleteCharAt(0);
            }
        }
        else {
            findCore(sb, resultList, candidates, map, n);
        }
        return resultList;
    }
    
    private void findCore(StringBuilder sb, List<String> resultList, List<String> candidates, Map<String, String> map, int n) {
        if (n == 0) {
            resultList.add(sb.toString());
            return;
        }
        for (String s : candidates) {
            if ((n == 2) && s.equals("0")) {
                continue;
            }
            sb.insert(0, s);
            sb.append(map.get(s));
            findCore(sb, resultList, candidates, map, n - 2);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(0);
        }
    }
}
