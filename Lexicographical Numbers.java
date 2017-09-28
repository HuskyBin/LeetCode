/*
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            addNum(i, resultList, n);
        }
        return resultList;
    }
    
    private void addNum(int value, List<Integer> resultList, int n) {
        if (value > n) {
            return;
        }
        resultList.add(value);
        for (int i = 0; i <= 9; i++) {
            addNum(value * 10 + i, resultList, n);
        }
    }
}
