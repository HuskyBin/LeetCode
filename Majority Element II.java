/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
*/
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        
        Integer num1 = null;
        Integer num2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num1 != null && num == num1) {
                count1 ++;
            }
            else if (num2 != null && num == num2) {
                count2 ++;
            }
            else if (count1 == 0) {
                count1 = 1;
                num1 = num;
            }
            else if (count2 == 0) {
                count2 = 1;
                num2 = num;
            }
            else {
                count1 --;
                count2 --;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num1 != null && num == num1) {
                count1++;
            }
            if (num2 != null && num == num2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            resultList.add(num1);
        }
        if (count2 > nums.length / 3) {
            resultList.add(num2);
        }
        return resultList;
    }
}
