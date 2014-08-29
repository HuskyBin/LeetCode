/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/
public class Solution {

    public int sumValue = Integer.MIN_VALUE;    
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length <= 2) {
            return 0;
        }
        Arrays.sort(num);
        sumValue = num[0] + num[1] + num[2];
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            boolean isFindTarget = findTheTarget(num, i + 1, target, num[i]);
            if (isFindTarget == true) {
                return target;
            }
        }
        return sumValue;
    }
    
    private boolean findTheTarget(int[] num, 
                               int index, 
                               int target, 
                               int firstNum) {
        int startIndex = index;
        int endIndex = num.length - 1;
        while (startIndex < endIndex) {
           if (Math.abs(firstNum + num[startIndex] + num[endIndex] - target) < Math.abs(target - sumValue)) {
               sumValue = firstNum + num[startIndex] + num[endIndex];
               if (sumValue == target) {
                   return true;
               }
           }
           if (num[startIndex] + num[endIndex] + firstNum < target) {
                startIndex++;
            }
            else {
                endIndex--;
            }
        }
        return false;
    }
}
