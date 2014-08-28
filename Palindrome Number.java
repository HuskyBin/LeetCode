/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int length = getNumberLength(x);
        while (x != 0) {
            int lastDigit = x % 10;
            int firstDigit = x / (int)(Math.pow(10.0, length - 1));
            if (lastDigit != firstDigit) {
                return false;
            }
            x = x % (int)Math.pow(10.0, length - 1);
            x = x / 10;
            length -= 2;
        }
        return true;
    }
    
    private int getNumberLength(int num) {
        int result = 0;
        while (num != 0) {
            result++;
            num = num / 10;
        }
        return result;
    }
}
