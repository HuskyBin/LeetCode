/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Subscribe to see which companies asked this question

Hide Tags
*/

//link: http://bookshadow.com/weblog/2015/12/03/leetcode-super-ugly-number/
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (primes == null) {
            return 0;
        }
        int[] q = new int[n];
        int[] index = new int[primes.length];
        int[] value = new int[primes.length];
        
        q[0] = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < primes.length; j++) {
                value[j] = q[index[j]] * primes[j];
            }
            q[i] = findMin(value);
            for (int j = 0; j < primes.length; j++) {
                if (value[j] == q[i]) {
                    index[j]++;
                }
            }
        }
        return q[n - 1];
    }
    
    private int findMin(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = Math.min(result, arr[i]);
        }
        return result;
    }
}
