/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/
class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chs = s.toCharArray();
        Character[] chas = new Character[chs.length];
        for (int i = 0; i < chas.length; i++) {
            chas[i] = Character.valueOf(chs[i]);
        }
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : chas) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Arrays.sort(chas, (a, b) -> {
            if((map.get(b) - map.get(a)) != 0) {
                    return map.get(b) - map.get(a);   
                }
                else {
                    return b - a;
                }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chas.length; i++) {
            sb.append(chas[i]);
        }
        return sb.toString();
    }
}
