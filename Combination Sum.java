/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return resultList;
        }
        Arrays.sort(candidates);
        List<Integer> singleList = new ArrayList<>();
        combinationsSumCore(candidates, target, singleList, resultList, 0);
        return resultList;
    }
    
    private void combinationsSumCore(int[] candidates, int target, List<Integer> singleList, List<List<Integer>> resultList, int index) {
        if (target == 0) {
            List<Integer> copyList = new ArrayList<>(singleList);
            resultList.add(copyList);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                singleList.add(candidates[i]);
                combinationsSumCore(candidates, target - candidates[i], singleList, resultList, i);
                singleList.remove(singleList.size() - 1);
            }
            else {
                break;
            }
        }
    }
}
