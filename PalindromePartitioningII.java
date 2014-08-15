/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.


Analysis:
This is a DP problem! Also it is a double DP problem!
Why? Because, if we use the same algorithm in the Palindrome Partitioning I, definitely it will expire the time limit. When you are facing the problem asking "return the minimum/maximum, best, shortest...", it is also a good direction targeting the DP (sometimes greedy also works fine). If you are not familiar with DP problem, here is a very good reading from topcoder. Generally speaking, DP is not very easy, and most of the company interviews will NOT cover the DP, so don't worry too much.

For this problem, firstly we consider the main part.
It is a good way to look for the "state", and the "optimal solution" based on that state, to solve the DP problem. In other words, if you found the correct state, and the function of the state representing the optimal solution, all you need is some loops and initialization implementing the algorithm.

Here the state is not too hard ----  minimum cut.   Define res[i] = the minimum cut from 0 to i in the string.
The result w =e need eventually is res[s.size()-1].
We know res[0]=0. Next we are looking for the optimal solution function f.
For example, let s = "leet".
f(0) = 0;  // minimum cut of str[0:0]="l", which is a palindrome, so not cut is needed.
f(1) = 1; // str[0:1]="le" How to get 1? 
                 f(1) might be:  (1)   f(0)+1=1, the minimum cut before plus the current char.
                                       (2)   0, if str[0:1] is a palindrome  (here "le" is not )
f(2) = 1; // str[0:2] = "lee" How to get 2?
                f(2) might be:   (1)  f(1) + 1=2
                                       (2)  0, if str[0:2] is a palindrome (here "lee" is not)
                                       (3)  f(0) + 1,  if str[1:2] is a palindrome, yes! 
f(3) = 2; // str[0:3] = "leet" How to get 2?
                f(3) might be:   (1)  f(2) + 1=2
                                       (2)  0, if str[0:3] is a palindrome (here "leet" is not)
                                       (3)  f(0) + 1,  if str[1:3] is a palindrome (here "eet" is not)
                                       (4)  f(1) + 1,  if str[2:e] is a palindrome (here "et" is not)
OK, output f(3) =2 as the result.

So, the optimal function is:

f(i) = min [  f(j)+1,  j=0..i-1   and str[j:i] is palindrome
                    0,   if str[0,i] is palindrome ]

The above algorithm works well for the smaller test cases, however for the big cases, it still cannot pass.
Why? The way we test the palindrome is time-consuming.

Also using the similar DP idea, we can construct the look-up table before the main part above, so that the palindrome testing becomes the looking up operation. The way we construct the table is also the idea of DP.
e.g.  mp[i][j]==true if str[i:j] is palindrome.
       mp[i][i]=true;
       mp[i][j] = true if str[i]==str[j] and (mp[i+1][j-1]==true or j-i<2 )  j-i<2 ensures the array boundary.

So, using this scheme to test the palindrome helps improve the efficiency and thus we can pass all the test cases. Details see the code below.



注意 在计算  isPalindrome的时候  i  是从 后往前  滑动的  不是从前往后， 因为  如果从前往后的话，，， 它会用到 yi个 p[i + 1][j - 1], 那么这个序列 是没有被计算过的。。 如果从后往前，  那边 当用到 p[i + 1][j - 1], 它就已经被计算过了。

或者可可以用 Length 从 2-n的方法 来计算  ， 这样就保证了 当要用到L-2 的时候   他是已经被计算过了的。
*/
public class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int len = 2; len <= s.length(); len++) {
            for (int start = 0; start + len - 1 < s.length(); start++) {
                int end = start + len - 1;
                if (len == 2) {
                    if (s.charAt(start) == s.charAt(end)) {
                        isPalindrome[start][end] = true;
                    }
                }
                else {
                    if (s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1] == true) {
                        isPalindrome[start][end] = true;
                    }
                }
            }
        }
        
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
        	int min = Integer.MAX_VALUE;
            for (int j = -1; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    if (j == -1) {
                        min = 0;
                        break;
                    }
                    else {
                    	min = Math.min(min, dp[j] + 1);
                    }
                }
            }
            dp[i] = min;
        }
        return dp[s.length() - 1];
    }
}
