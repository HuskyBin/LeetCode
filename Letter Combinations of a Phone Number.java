/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
*/
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            resultList.add("");
            return resultList;
        }
        StringBuilder sb = new StringBuilder();
        letterCombinationsCore(digits, 0, sb, resultList);
        return resultList;
    }
    
    private void letterCombinationsCore(String digits, int index, StringBuilder sb, List<String> resultList) {
        if (index == digits.length()) {
            resultList.add(sb.toString());
            return;
        }
        String lettleStr = findPossibleLetter(digits.charAt(index));
        for (int i = 0; i < lettleStr.length(); i++) {
            char curChar = lettleStr.charAt(i);
            sb.append(curChar);
            letterCombinationsCore(digits, index + 1, sb, resultList);
            sb.setLength(sb.length() - 1);
        }
    }
    
    private String findPossibleLetter(char curChar) {
        if (curChar == '2') {
            return "abc";
        }
        else if (curChar == '3') {
            return "def";
        }
        else if (curChar == '4') {
            return "ghi";
        }
        else if (curChar == '5') {
            return "jkl";
        }
        else if (curChar == '6') {
            return "mno";
        }
        else if (curChar == '7') {
            return "pqrs";
        }
        else if (curChar == '8') {
            return "tuv";
        }
        else if (curChar == '9') {
            return "wxyz";
        }
        else if(curChar == '0') {
            return " ";
        }
        else {
            return "";
        }
    }
}
