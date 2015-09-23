/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (k == 0 || n <= 0) {
            return resultList;
        }

        List<Integer> solution = new ArrayList<>();
        combinationSum3Core(k , n, solution, resultList, 1, 0);
        return resultList;
    }

    private void combinationSum3Core(int k, int n, List<Integer> solution, List<List<Integer>> resultList, int start, int sum) {
        if (sum == n && k == solution.size()) {
            List<Integer> oneSolution = new ArrayList<>(solution);
            resultList.add(oneSolution);
            return;
        }
        if (k == solution.size()) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (sum + i <= n) {
                solution.add(i);
                combinationSum3Core(k, n, solution, resultList, i + 1, sum + i);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
