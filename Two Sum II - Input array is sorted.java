
// test

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
