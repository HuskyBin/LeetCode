/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        if (num == null || num.length == 0) {
            List<List<Integer>> result = new ArrayList<>();
            return result;
        }
        Arrays.sort(num);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            int target = 0 - num[i];
            findTheTarget(num, i + 1, target, resultList, num[i]);
        }
        return resultList;
    }
    
    private void findTheTarget(int[] num, 
                               int index, 
                               int target, 
                               List<List<Integer>> resultList, 
                               int firstNum) {
        int startIndex = index;
        int endIndex = num.length - 1;
        while (startIndex < endIndex) {
            if (num[startIndex] + num[endIndex] == target) {
                List<Integer> newResult = new ArrayList<>();
                newResult.add(firstNum);
                newResult.add(num[startIndex]);
                newResult.add(num[endIndex]);
                resultList.add(newResult);
                startIndex++;
                endIndex--;
                while (startIndex < num.length - 1 && num[startIndex] == num[startIndex - 1]) {
                    startIndex++;
                }
                while (endIndex > 0 && num[endIndex] == num[endIndex + 1]) {
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
