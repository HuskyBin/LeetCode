/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/
// Better solution

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        List<Integer> oneResult = new ArrayList<>();
        dfsCore(n, results, oneResult, 2);
        return results;
    }
    
    private void dfsCore(int n, List<List<Integer>> results, List<Integer> oneResult, int start) {
        if (n == 1) {
            if (oneResult.size() > 1) {
                results.add(new ArrayList<>(oneResult));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                oneResult.add(i);
                dfsCore(n / i, results, oneResult, i);
                oneResult.remove(oneResult.size() - 1);
            }
        }  
    }
}


public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> oneResult = new ArrayList<>();
        getCore(n, result, oneResult, 0);
        return result;
    }
    
    private void getCore(int n, List<List<Integer>> result, List<Integer> oneResult, int last) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0 && i >= last) {
                oneResult.add(i);
                oneResult.add(n / i);
                List<Integer> newList = new ArrayList<>(oneResult);
                result.add(newList);
                oneResult.remove(oneResult.size() - 1);
                getCore(n / i, result, oneResult, i);
                oneResult.remove(oneResult.size() - 1);
            }
        }
    }
}
