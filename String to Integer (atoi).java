/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
public class Solution {
    public int atoi(String str) {
         if (str == null || str.length() == 0) {
             return 0;
         }
         boolean signNegative = false;
         int startIndex = 0;
         int endIndex = str.length() - 1;
         while (startIndex <= endIndex && str.charAt(startIndex) == ' ') {
             startIndex++;
         }
         if (startIndex > endIndex) {
             return 0;
         }
         if (str.charAt(startIndex) == '+') {
             signNegative = false;
             startIndex++;
         }
         else if (str.charAt(startIndex) == '-') {
             signNegative = true;
             startIndex++;
         }
         int result = 0;
         boolean overFlow = false;
         while (startIndex <= endIndex) {
             if (str.charAt(startIndex)  <= '9' && str.charAt(startIndex) >= '0') {
                 if ((Integer.MAX_VALUE - Character.getNumericValue(str.charAt(startIndex))) / 10 < result) {
                    overFlow = true;
                    break;
                 }
                 result = result * 10 + Character.getNumericValue(str.charAt(startIndex));
             }
             else {
                 break;
             }
             startIndex++;
         }
         if (overFlow == true) {
             if (signNegative == true) {
                 return Integer.MIN_VALUE;
             }
             else {
                 return Integer.MAX_VALUE;
             }
         }
         if (signNegative == true) {
             return 0 - result;
         }
         return result;
    }
}
