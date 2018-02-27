/*
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X. A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:

N  will be in range [1, 10000].
*/
class Solution {
  public int rotatedDigits(int N) {
    
    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (isValid(i)) {
        result++;
      }
    }
    return result;
  }
  
  private boolean isValid(int number) {
    boolean validFound = false;
    
    while (number > 0) {
      int lastDigit = number % 10;
      if (lastDigit == 2 || lastDigit == 6 || lastDigit == 5 || lastDigit == 9) {
        validFound = true;
      } 
      if (lastDigit == 3 || lastDigit == 7 || lastDigit == 4) {
        return false;
      }
      number /= 10;
    }
    return validFound;
  }
}
