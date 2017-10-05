/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

发现了可以用优化过的递归来解，递归的方法基本上等于brute force，但是C++版本的直接递归没法通过OJ，而是要先给数组从大到小的顺序排序，
这样大的数字先加，如果超过target了，就直接跳过了后面的再次调用递归的操作，效率会提高不少，所以会通过OJ。下面来看代码，
我们建立一个长度为4的数组sums来保存每个边的长度和，我们希望每条边都等于target，数组总和的四分之一。然后我们遍历sums中的每条边，
我们判断如果加上数组中的当前数字大于target，那么我们跳过，如果没有，我们就加上这个数字，然后对数组中下一个位置调用递归，如果返回为真，
我们返回true，否则我们再从sums中对应位置将这个数字减去继续循环，参见代码如下：
*/
class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }   
        int[] sumArr = new int[4];
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Integer[] newNums = IntStream.of(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(newNums, Comparator.reverseOrder());
        return makesquareCore(newNums, sumArr, 0, sum / 4);
    }
    
    private boolean makesquareCore(Integer[] nums, int[] sumArr, int index, int target) {
        if (index >= nums.length) {
            return sumArr[0] == target && sumArr[1] == target && sumArr[2] == target;
        }
        for (int i = 0; i < 4; i++) {
            if (sumArr[i] + nums[index] > target) {
                continue;
            }
            sumArr[i] += nums[index];
            if (makesquareCore(nums, sumArr, index + 1, target)) {
                return true;
            }
            sumArr[i] -= nums[index];
        }
        return false;
    }
}
