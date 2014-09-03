/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "";
        } 
        List<String> tempStringList = new ArrayList<>();
        multiplyCore(num1, num2, tempStringList);
        String result = addAllTempString(tempStringList);
        return result;
    }
    
    private void multiplyCore(String num1, String num2, List<String> tempStringList) {
        for (int i = num1.length() - 1; i >= 0; i--) {
            char curChar = num1.charAt(i);
            String subResult = multiplySingleDigit(curChar, num2);
            StringBuilder sb = new StringBuilder();
            sb.append(subResult);
            int temp = num1.length() - 1 - i;
            while (temp > 0 && !subResult.equals("0")) {
                sb.append('0');
                temp--;
            }
            tempStringList.add(sb.toString());
        }
    }
    
    private String multiplySingleDigit(char digit, String num2) {
        if (digit == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            char curChar = num2.charAt(i);
            int sum = Character.getNumericValue(curChar) * Character.getNumericValue(digit) + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
    
    private String addAllTempString(List<String> tempStringList) {
        String result = "0";
        for (int i = 0; i < tempStringList.size(); i++) {
            String curString = tempStringList.get(i);
            result = addTwoString(result, curString);
        }
        return result;
    }
    
    private String addTwoString(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int index1 = str1.length() - 1;
        int index2 = str2.length() - 1;
        int carry = 0;
        while (index1 >= 0 || index2 >= 0) {
            char cur1 = (index1 >= 0) ? str1.charAt(index1) : '0';
            char cur2 = (index2 >= 0) ? str2.charAt(index2) : '0';
            int sum = Character.getNumericValue(cur1) + Character.getNumericValue(cur2) + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            index1 = (index1 >= 0) ? index1 - 1 : index1;
            index2 = (index2 >= 0) ? index2 - 1 : index2;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
