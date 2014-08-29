/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/
public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (num == null || num.length <= 3) {
            return resultList;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            find3Sum(num, i + 1, resultList, target - num[i], num[i]);
        }
        return resultList;
    }
    
    private void find3Sum(int[] num, int index, List<List<Integer>> resultList, int target, int firstNum) {
        for (int i = index; i < num.length; i++) {
            if (i > index && num[i] == num[i - 1]) {
                continue;
            }
            findTwoSum(num, i + 1, resultList, target - num[i], firstNum, num[i]);
        }        
    }
    
    private void findTwoSum(int[] num, int index, List<List<Integer>> resultList, int target, int firstNum, int secondNum) {
        int startIndex = index;
        int endIndex = num.length - 1;
        while (startIndex < endIndex) {
            if (num[startIndex] + num[endIndex] == target) {
                List<Integer> oneResult = new ArrayList<>();
                oneResult.add(firstNum);
                oneResult.add(secondNum);
                oneResult.add(num[startIndex]);
                oneResult.add(num[endIndex]);
                resultList.add(oneResult);
                startIndex++;
                endIndex--;
                while (startIndex < num.length && num[startIndex] == num[startIndex - 1]) {
                    startIndex++;
                }
                while (endIndex >= 0 && num[endIndex] == num[endIndex + 1]) {
                    endIndex--;
                }
            }
            else if (num[startIndex] + num[endIndex] < target) {
                startIndex++;
            }
            else {
                endIndex--;
            }
        }
    }
}
