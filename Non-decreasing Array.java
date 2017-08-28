class Solution {
   public boolean checkPossibility(int[] nums) {
    if (nums == null || nums.length < 2) {
            return true;
        }
        int first = Integer.MIN_VALUE;
        int second = nums[0];

        boolean hasDescrease = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < second) {
                if (hasDescrease) {
                    return false;
                }
                hasDescrease = true;
                if (nums[i] >= first) {
                    first = second;
                    second = nums[i];
                }
                else {
                    first = second;
                }
            }
            else {
                first = second;
                second = nums[i];
            }
        }
        return true;
   } 
}
