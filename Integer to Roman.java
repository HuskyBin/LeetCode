/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/
public class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        char[] roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int digit = 3;
        int index = 6;
        while (num > 0) {
            int lastNum = num / (int)Math.pow(10.0, digit);
            addRomanCharIntoSB(sb, roman, index, lastNum);
            num = num - lastNum * (int)Math.pow(10.0, digit);
            digit--;
            index -= 2;
        }
        return sb.toString();
    }
    
    private void addRomanCharIntoSB(StringBuilder sb, char[] roman, int index, int number) {
        if (number == 0) {
            return;
        }
        if (number <= 3) {
            while (number > 0) {
                sb.append(roman[index]);
                number--;
            }
        }
        else if (number == 4) {
            sb.append(roman[index]);
            sb.append(roman[index + 1]);
        }
        else if (number == 5) {
            sb.append(roman[index + 1]);
        }
        else if (number > 5 && number < 9) {
            sb.append(roman[index + 1]);
            while (number > 5) {
                sb.append(roman[index]);
                number--;
            }
        }
        else {
            sb.append(roman[index]);
            sb.append(roman[index + 2]);
        }
    }
}
