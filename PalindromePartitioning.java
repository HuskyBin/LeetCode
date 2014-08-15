/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> resultList = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return resultList;
        }
        List<String> singleList = new ArrayList<>();
        partitionCore(s, resultList, singleList, 0);
        return resultList;
    }
    
    public void partitionCore(String s, List<List<String>> resultList, List<String> singleList, int index) {
        if (index == s.length()) {
            List<String> copyList = new ArrayList<>(singleList);
            resultList.add(copyList);
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                singleList.add(s.substring(index, i));
                partitionCore(s, resultList, singleList, i);
                singleList.remove(singleList.size() - 1);
            }
        }
    }
    
    public boolean isPalindrome(String s, int startIndex, int endIndex) {
        int start = startIndex;
        int end = endIndex - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
