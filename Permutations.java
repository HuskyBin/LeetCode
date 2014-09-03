/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/
public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (num == null || num.length == 0) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        boolean[] visited = new boolean[num.length];
        permuteCore(num, singleList, resultList, visited);
        return resultList;
    }
    
    private void permuteCore(int[] num, List<Integer> singleList, List<List<Integer>> resultList, boolean[] visited) {
        if (singleList.size() == num.length) {
            resultList.add(new ArrayList<>(singleList));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                singleList.add(num[i]);
                permuteCore(num, singleList, resultList, visited);
                visited[i] = false;
                singleList.remove(singleList.size() - 1);
            }
        }
    }
}
