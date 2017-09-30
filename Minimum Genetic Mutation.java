/*
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3
*/
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if (start == null || end == null || bank == null) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String curStr = queue.poll();
                Set<String> nextStr = findNext(curStr, visited, bank);
                if (nextStr.contains(end)) {
                    return level;
                }
                for (String newStr : nextStr) {
                    queue.add(newStr);
                    visited.add(newStr);
                }
            }
        }
        return -1;
    }
    
    private Set<String> findNext(String curStr, Set<String> visited, String[] bank) {
        Set<String> result = new HashSet<>();
        for (String gen : bank) {
            if (!visited.contains(gen) && isClose(curStr, gen)) {
                result.add(gen);
            }
        }
        return result;
    }
    
    private boolean isClose(String curStr, String newStr) {
        if (curStr.length() != newStr.length()) {
            return false;
        }
        boolean isOneDiff = false;
        for (int i = 0; i < curStr.length(); i++) {
            if (curStr.charAt(i) != newStr.charAt(i)) {
                if (isOneDiff == false) {
                    isOneDiff = true;    
                }
                else {
                    return false;
                }
            }
        }
        return isOneDiff;
    }
}
