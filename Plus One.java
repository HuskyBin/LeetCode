/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/
public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int carry = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry == 1) {
            sb.append(1);
        }
        int[] newDigits = new int[sb.length()];
        for (int i = sb.length() - 1; i >= 0; i--) {
            newDigits[sb.length() - 1 - i] = Character.getNumericValue(sb.charAt(i));
        }
        return newDigits;
    }
}
