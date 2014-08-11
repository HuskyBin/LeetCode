public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (num == null) {
            return resultList;
        }
        Arrays.sort(num);
        resultList.add(new ArrayList<Integer>());
        List<Integer> singleList = new ArrayList<>();
        subsetsCore(num, resultList, singleList, 0);
        return resultList;
    }
    
    public void subsetsCore(int[] num, List<List<Integer>> resultList, List<Integer> singleList, int index) {
        for (int i = index; i < num.length; i++) {
            singleList.add(num[i]);
            List<Integer> copyList = new ArrayList<>(singleList);
            resultList.add(copyList);
            subsetsCore(num, resultList, singleList, i + 1);
            singleList.remove(singleList.size() - 1);
            while (i < num.length - 1 && num[i] == num[i + 1]) {
                i++;
            }
        }
    }
}
