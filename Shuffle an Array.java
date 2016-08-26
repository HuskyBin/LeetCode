/*
// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
*/
public class Solution {
    
    public int[] num;
    public int[] constantNum;

    public Solution(int[] nums) {
        num = new int[nums.length];
        constantNum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            num[i] = nums[i];
            constantNum[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for (int i = 0; i < constantNum.length; i++) {
            num[i] = constantNum[i];
        }
        return num;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = num.length; i > 1; i--) {
            int nextRandom = random.nextInt(i);
            int temp = num[i - 1];
            num[i - 1] = num[nextRandom];
            num[nextRandom] = temp;
        }
        return num;
    }
}
