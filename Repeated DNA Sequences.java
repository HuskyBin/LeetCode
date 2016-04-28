/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = (sum * 4 + mapDNA(s.charAt(i))) & 0xFFFFF;
            if (i < 9) {
                continue;
            }
            if (map.containsKey(sum)) {
                if (map.get(sum) == 1) {
                    result.add(s.substring(i - 9, i + 1));    
                }
                map.put(sum, 2);
            }
            else {
                map.put(sum, 1);
            }
        }
        return result;
    }
    
    private int mapDNA(char c) {
        if (c == 'A') {
            return 0;
        }
        else if (c == 'C') {
            return 1;
        }
        else if (c == 'G') {
            return 2;
        }
        else {
            return 3;
        }
        
    }
}
