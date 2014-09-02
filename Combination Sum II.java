/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (num == null || num.length == 0) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        Arrays.sort(num);
        combinationSumCore(num, target, singleList, resultList, 0);
        return resultList;
    }
    
    private void combinationSumCore(int[] num, int target, List<Integer> singleList, List<List<Integer>> resultList, int index) {
        if (target == 0) {
            resultList.add(new ArrayList<>(singleList));
            return;
        }
        for (int i = index; i < num.length; i++) {
            if (num[i] <= target) {
                singleList.add(num[i]);
                combinationSumCore(num, target - num[i], singleList, resultList, i + 1);
                singleList.remove(singleList.size() - 1);
            }
            while(i < num.length - 1 && num[i] == num[i + 1]) {
                i++;
            }
        }
    }
}
