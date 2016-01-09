/*
Total Accepted: 3119 Total Submissions: 8710 Difficulty: Easy
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question
*/
public class Solution {
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("10*");
    }
}
