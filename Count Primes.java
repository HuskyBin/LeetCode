/*
Description:

Count the number of prime numbers less than a non-negative number, n

click to show more hints.

References:
How Many Primes Are There?

Sieve of Eratosthenes

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/

public class Solution {
    public int countPrimes(int n) {
        if (n <= 0) {
            return 0;
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 2; i * i < n; i++) {
            if (visited[i] == false) {
                for (int j = 2; j * i < n; j++) {
                    visited[i * j] = true;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if(visited[i] == false) {
                count++;
            }
        }
        return count;
    }
}
