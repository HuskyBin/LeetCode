/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/
public class Solution {
    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        String nextOne = "1";
        for (int i = 2; i <= n; i++) {
            nextOne = getNextOne(nextOne); 
        }
        return nextOne;
    }
    
    private String getNextOne(String str) {
        int startIndex = 0;
        StringBuilder sb = new StringBuilder();
        while (startIndex < str.length()) {
            int forwardIndex = startIndex + 1;
            while (forwardIndex < str.length() && str.charAt(forwardIndex) == str.charAt(startIndex)) {
                forwardIndex++;
            } 
            sb.append(forwardIndex - startIndex);
            sb.append(str.charAt(startIndex));
            startIndex = forwardIndex;
        }
        return sb.toString();
    }
}
