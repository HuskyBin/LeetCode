/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

思想是： 从高位到地位一个一个去判断那个最大的数 在这位上应该是1 还是0. 判断的思路是，先取出所有数的prefix（到这位为止的），然后假设最大数在这位上能取得
1， 然后用这假设的max 去和prefix 异或，如果异或的结果也在这些prefix 中，那么这位就该取1，否则就取0.  后面以此类推。
*/
class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            Set<Integer> set = new HashSet<>();
            mask |= 1 << i;
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(prefix ^ tmp)) {
                    max = tmp;
                }
            }
        }
        return max;
    }
}
