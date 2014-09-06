/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        combineCore(n, k, singleList, resultList, 1);
        return resultList;
    }
    
    private void combineCore(int n, int k, List<Integer> singleList, List<List<Integer>> resultList, int index) {
        if (singleList.size() == k) {
            resultList.add(new ArrayList<>(singleList));
            return;
        }
        for (int i = index; i <= n; i++) {
            singleList.add(i);
            combineCore(n, k, singleList, resultList, i + 1);
            singleList.remove(singleList.size() - 1);
        }
    }
}
