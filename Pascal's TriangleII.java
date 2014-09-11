// Only O(K) space complexity
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        List<Integer> resultList = new ArrayList<>(rowIndex);
        resultList.add(1);
        if (rowIndex == 0) {
            return resultList;
        }
        for (int i = 1; i <= rowIndex; i++) {
            int temp = resultList.get(0);
            for (int j = 1; j < i; j++) {
                int element = resultList.get(j) + temp;
                temp = resultList.get(j);
                resultList.set(j, element);
            }
            resultList.add(1);
        }
        return resultList;
    }
}
