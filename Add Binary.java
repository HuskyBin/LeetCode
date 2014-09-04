/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int carry = 0;
        while (aIndex >= 0 || bIndex >= 0) {
            char aChar = (aIndex >= 0) ? a.charAt(aIndex) : '0';
            char bChar = (bIndex >= 0) ? b.charAt(bIndex) : '0';
            int sum = Character.getNumericValue(aChar) + Character.getNumericValue(bChar) + carry;
            carry = sum / 2;
            sb.append(sum % 2);
            if (aIndex >= 0) {
                aIndex--;
            }
            if (bIndex >= 0) {
                bIndex--;
            }
        }
        if (carry > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}
