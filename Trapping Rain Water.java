/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*/
// Be carefull this case [2,0,2], that means you need caculate just once for this equal case.
// Best Solution
class Solution {
    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (leftMax < height[left]) {
                    leftMax = height[left];
                } else {
                    result += leftMax - height[left];
                }
                left++;
            } else {
                if (rightMax < height[right]) {
                    rightMax = height[right];
                } else {
                    result += rightMax - height[right];
                }
                right--;
            }
        }
        return result;
    }
}

public class Solution {
    public int trap(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        while (startIndex < A.length) {
            endIndex = startIndex + 1;
            while (endIndex < A.length && A[endIndex] < A[startIndex]) {
                endIndex++;
            }
            if (endIndex == A.length) {
                break;
            }
            result += (endIndex - startIndex - 1) * A[startIndex];
            for (int i = startIndex + 1; i < endIndex; i++) {
                result -= A[i];
            }
            startIndex = endIndex;
        }
        
        endIndex = A.length - 1;
        while (endIndex >= 0) {
            startIndex = endIndex - 1;
            while (startIndex >= 0 && A[startIndex] <= A[endIndex]) {
                startIndex--;
            }
            if (startIndex < 0) {
                break;
            }
            result += (endIndex - startIndex - 1) * A[endIndex];
            for (int i = startIndex + 1; i < endIndex; i++) {
                result -= A[i];
            }
            endIndex = startIndex;
        }
        return result;
    }
}
