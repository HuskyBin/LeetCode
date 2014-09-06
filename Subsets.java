/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (S == null || S.length == 0) {
            return resultList;
        }
        Arrays.sort(S);
        resultList.add(new ArrayList<Integer>());
        List<Integer> singleList = new ArrayList<>();
        subsetsCore(S, 0, singleList, resultList);
        return resultList;
    }
    
    private void subsetsCore(int[] S, int index, List<Integer> singleList, List<List<Integer>> resultList) {
        for (int i = index; i < S.length; i++) {
            singleList.add(S[i]);
            resultList.add(new ArrayList<>(singleList));
            subsetsCore(S, i + 1, singleList, resultList);
            singleList.remove(singleList.size() - 1);
        }
    }
}
