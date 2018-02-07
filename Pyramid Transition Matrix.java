/*
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.

For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
*/
class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if (bottom == null || bottom.length() == 0 || allowed == null) {
            return false;
        }
        Map<String, Set<String>> allowMap = new HashMap<>();
        for (String str : allowed) {
            String firstTwoSubStr = str.substring(0, 2);
            String lastCharStr = str.substring(str.length() - 1);
            allowMap.computeIfAbsent(firstTwoSubStr, k -> new HashSet<String>())
                .add(lastCharStr);
        }
        Set<String> visited = new HashSet<>();
        return checkCore(bottom, visited, allowMap);
    }
    
    private boolean checkCore(String level, Set<String> visited, Map<String, Set<String>> allowMap) {
        if (visited.contains(level)) {
            return false;
        }
        visited.add(level);
        if (level.length() <= 1) {
            return true;
        }
        List<String> nextLevel = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (getList(level, 0, sb, nextLevel, allowMap) == false) {
            return false;
        }
        for (String nextStr : nextLevel) {
            if (checkCore(nextStr, visited, allowMap) == true) {
                return true;
            }
        }
        return false;
    }
    
    private boolean getList(String str, int index, StringBuilder sb, List<String> levelList, Map<String, Set<String>> allowMap) {
        if (index == str.length() - 1) {
            levelList.add(sb.toString());
            return true;
        }
        String subStr = str.substring(index, index + 2);
        if (!allowMap.containsKey(subStr)) {
            return false;
        }
        for (String s : allowMap.get(subStr)) {
            sb.append(s);
            if (getList(str, index + 1, sb, levelList, allowMap) == false) {
                return false;
            }
            sb.setLength(sb.length() - 1);
        }
        return true;
    }
}
