/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (num == null || num.length == 0) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        Arrays.sort(num);
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
                while (i < num.length - 1 && num[i] == num[i + 1]) {
                    i++;
                }
            }
        }
    }
}
