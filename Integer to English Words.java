/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
*/
public class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> level = new ArrayList<>();
        level.add("");
        level.add("Thousand ");
        level.add("Million ");
        level.add("Billion ");
        List<String> oneDigit = new ArrayList<>();
        oneDigit.add("");
        oneDigit.add("One ");
        oneDigit.add("Two ");
        oneDigit.add("Three ");
        oneDigit.add("Four ");
        oneDigit.add("Five ");
        oneDigit.add("Six ");
        oneDigit.add("Seven ");
        oneDigit.add("Eight ");
        oneDigit.add("Nine ");
        oneDigit.add("Ten ");
        oneDigit.add("Eleven ");
        oneDigit.add("Twelve ");
        oneDigit.add("Thirteen ");
        oneDigit.add("Fourteen ");
        oneDigit.add("Fifteen ");
        oneDigit.add("Sixteen ");
        oneDigit.add("Seventeen ");
        oneDigit.add("Eighteen ");
        oneDigit.add("Nineteen ");
        List<String> twoDigit = new ArrayList<>();
        twoDigit.add("");
        twoDigit.add("");
        twoDigit.add("Twenty ");
        twoDigit.add("Thirty ");
        twoDigit.add("Forty ");
        twoDigit.add("Fifty ");
        twoDigit.add("Sixty ");
        twoDigit.add("Seventy ");
        twoDigit.add("Eighty ");
        twoDigit.add("Ninety ");
        
        StringBuilder sb = new StringBuilder();
        int number = num;
        int levelIndex = 0;
        while (number > 0) {
            int threeDigit = number % 1000;
            if (threeDigit > 0) {
                sb.insert(0, level.get(levelIndex));
            }
            int lastTwoDigit = threeDigit % 100;
            if (lastTwoDigit < 20) {
                sb.insert(0, oneDigit.get(lastTwoDigit));
            }
            else {
                sb.insert(0, oneDigit.get(threeDigit % 10));
                sb.insert(0, twoDigit.get((threeDigit / 10) % 10));
            }
            if (threeDigit > 99) {
                sb.insert(0, "Hundred ");
                sb.insert(0, oneDigit.get((threeDigit / 100) % 10));
            }
            levelIndex++;
            number /= 1000;
        }
        return sb.toString().trim();
    }
}
