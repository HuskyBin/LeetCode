/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.

*/
// concise solution
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> resultList = new ArrayList<>();
        if (S == null) {
            return resultList;
        }
        dfsCore(S, 0, resultList);
        return resultList;
    }
    
    private void dfsCore(String str, int index, List<String> resultList) {
        if (index == str.length()) {
            resultList.add(str);
            return;
        }
        char curChar = str.charAt(index);
        if (Character.isLetter(curChar)) {
            String oneResult = str.substring(0, index) + Character.toUpperCase(curChar) + str.substring(index + 1);
        
            dfsCore(oneResult, index + 1, resultList);
            String secondResult = str.substring(0, index) + Character.toLowerCase(curChar) + str.substring(index + 1);
            dfsCore(secondResult, index + 1, resultList);    
        } else {
            dfsCore(str, index + 1, resultList);
        }
    }
}

// my solution
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> resultList = new ArrayList<>();
        if (S == null) {
            return resultList;
        }
        resultList.add(S);
        dfsCore(S, 0, resultList);
        return resultList;
    }
    
    
    private void dfsCore(String str, int index, List<String> resultList) {
        if (index == str.length()) {
            return;
        }
        char curChar = str.charAt(index);
        if (curChar >= 'a' && curChar <= 'z') {
            String oneResult = str.substring(0, index) + Character.toUpperCase(curChar) + str.substring(index + 1);
            resultList.add(oneResult);
            dfsCore(oneResult, index + 1, resultList);
        } else if (curChar >= 'A' && curChar <= 'Z') {
            String oneResult = str.substring(0, index) + Character.toLowerCase(curChar) + str.substring(index + 1);
            dfsCore(oneResult, index + 1, resultList);
            resultList.add(oneResult);
        }
        dfsCore(str, index + 1, resultList);
    }
}
