/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int pre = lower - 1;
        int cur = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length) {
                cur = upper + 1;
            }
            else {
                cur = nums[i];
            }
            if (cur - pre > 1) {
                result.add(generate(pre + 1, cur - 1));
            }
            pre = cur;
        }
        
        return result;
    }
    
    private String generate(int from, int to) {
        if (from == to) {
            return String.valueOf(from);
        }
        else {
            return String.valueOf(from) + "->" + String.valueOf(to);
        }
    }
}
