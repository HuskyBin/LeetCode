/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] temp = nums.clone();
        Arrays.sort(temp);
        
        int start = 0;
        while (start < nums.length && nums[start] == temp[start]) {
            start++;
        }
        int end = nums.length - 1;
        while (end >= start && nums[end] == temp[end]) {
            end--;
        }
        return end - start + 1;
    }
}

// O(n)
从左往右遍历的话，如果有序的话当前位置一定是已经遍历到的最大值位置；从右往左遍历的话，当前位置一定是已经遍历到的最小值位置。如果不是就要记录下beg和end。


另外初始赋值beg和end也很巧妙，保证如果完全有序的话返回0。如果不是完全有序的话必定beg和end都会被更新。

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len=nums.length;
        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        int start=-1, end=-1;
        
        for(int i=0; i<len; i++){
            max = Math.max(max, nums[i]); //from left to right, search the current max
            min = Math.min(min, nums[len-i-1]);  //from right to left, search the current min 
            
            if(nums[i] < max)  
                end = i;
            if(nums[len-i-1] > min)
                start = len-i-1;
        }
        
        if(start==-1) //the entire array is already sorted
            return 0;
        
        return end-start+1;
    }
}
