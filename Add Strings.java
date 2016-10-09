/*
Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/


// Shorter Solution
public class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return (num1 == null) ? num2 : num1;
        }
        int index = num1.length() - 1;
        int longerIndex = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (index >= 0 || longerIndex >= 0 || carry > 0) {
            int sum = carry;
            if (index >= 0)  sum += num1.charAt(index) - '0';
            if (longerIndex >= 0) sum += num2.charAt(longerIndex) - '0';
            carry =sum / 10;
            sb.append(sum % 10);
            index--;
            longerIndex--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}

// Longer one
public class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return (num1 == null) ? num2 : num1;
        }
        String shorter = (num1.length() < num2.length()) ? num1: num2;
        String longer= (num1.length() < num2.length()) ? num2 : num1;
        
        int carry = 0;
        int index = shorter.length() - 1;
        int longerIndex = longer.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (index >= 0) {
            int shorterDigit = shorter.charAt(index) - '0';
            int longerDigit = longer.charAt(longerIndex) - '0';
            int sumDigit = (shorterDigit + longerDigit + carry) % 10;
            carry = (shorterDigit + longerDigit + carry) / 10;
            sb.append(sumDigit);
            index--;
            longerIndex--;
        }
        while (longerIndex >= 0) {
            int longerDigit = longer.charAt(longerIndex) - '0';
            int sumDigit = (longerDigit + carry) % 10;
            carry = (longerDigit + carry) / 10;
            sb.append(sumDigit);
            longerIndex--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
