
// test
/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
are not zero-based\

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
import java.util.*;

public class TwoSumII {
    public static void main(String[] args) {
        TwoSumII obj = new TwoSumII();
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(3);
        nums.add(5);
        nums.add(7);
        nums.add(9);
        nums.add(10);
        nums.add(11);

        List<Integer> resultList = obj.towSum(nums, 10);
        for (Integer i : resultList) {
            System.out.println(i);
        }

    }

    public List<Integer> towSum(List<Integer> nums, int target) {
        List<Integer> resultList = new ArrayList<>();
        if (nums == null || nums.size() <= 1) {
            return resultList;
        }

        int start = 0;
        int end = nums.size() - 1;
        while (start < end) {
            int startNum = nums.get(start);
            int endNum = nums.get(end);
            if (startNum + endNum < target) {
                start++;
            }
            else if (startNum + endNum > target) {
                end--;
            }
            else {
                resultList.add(start);
                resultList.add(end);
                break;
            }
        }
        return resultList;
    } 
}
