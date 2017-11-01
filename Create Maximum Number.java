/*
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
*/
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = Math.max(k - nums2.length, 0); i <= Math.min(nums1.length, k); i++) {
            int[] res1 = getMaxSub(nums1, i);
            int[] res2 = getMaxSub(nums2, k - i);
            int[] res = new int[k];
            
            int pos1 = 0;
            int pos2 = 0;
            int tpos = 0;
            while (pos1 < res1.length || pos2 < res2.length) {
                res[tpos++] = (greater(res1, pos1, res2, pos2)) ? res1[pos1++] : res2[pos2++];
            }
            if (greater(res, 0, ans, 0)) {
                ans = res;
            }
        }
        return ans;
    }
    
    private boolean greater(int[] res1, int pos1, int[] res2, int pos2) {
        for (; pos1 < res1.length && pos2 < res2.length; pos1++, pos2++) {
            if (res1[pos1] < res2[pos2]) {
                return false;
            }
            if (res1[pos1] > res2[pos2]) {
                return true;
            }
        }
        return pos1 != res1.length;
    }
    
    private int[] getMaxSub(int[] num, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < num.length; i++) {
            while (len > 0 && len + num.length - i > k && res[len - 1] < num[i]) {
                len--;
            }
            if (len < k) {
                res[len++] = num[i];
            }    
        }
        return res;
    }
}
