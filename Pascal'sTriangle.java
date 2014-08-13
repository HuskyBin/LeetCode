public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (numRows <= 0) {
            return resultList;
        }
        List<Integer> singleList = new ArrayList<>();
        singleList.add(1);
        resultList.add(singleList);
        if (numRows == 1) {
            return resultList;
        }
        for (int i = 1; i < numRows; i++) {
            List<Integer> preLineList = resultList.get(resultList.size() - 1);
            List<Integer> newLine = new ArrayList<>();
            newLine.add(1);
            for (int j = 0; j < preLineList.size() - 1; j++) {
                int element = preLineList.get(j) + preLineList.get(j + 1);
                newLine.add(element);
            }
            newLine.add(1);
            resultList.add(newLine);
        }
        return resultList;
    }
}
