/*
This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.

Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
*/
// O(n)
Algorithm: Iterate through the array, each time all elements to the left are smaller (or equal) to all elements to the right, there is a new chunck.
Use two arrays to store the left max and right min to achieve O(n) time complexity. Space complexity is O(n) too.
This algorithm can be used to solve ver1 too.

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
        }

        return res + 1;
    }
}


/*
先copy再排序，得到拍完后数的index，还是用上一个双指针的方法，但是注意，对于duplicate的number，它的index是回自动加1的。
*/
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null) {
            return 0;
        }
        Set<Integer> duplicate = new HashSet<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copyArr);
        for (int i = 0; i < copyArr.length; i++) {
            if (!indexMap.containsKey(copyArr[i])) {
                indexMap.put(copyArr[i], i);
            } else {
                duplicate.add(copyArr[i]);
            }
        }
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < arr.length) {
            int endValue = indexMap.get(arr[endIndex]);
            while (startIndex <= endValue) {
                endValue = Math.max(indexMap.get(arr[startIndex]), endValue);
                if (duplicate.contains(arr[startIndex])) {
                    indexMap.put(arr[startIndex], indexMap.get(arr[startIndex]) + 1);
                }
                startIndex++;
            }
            result++;
            if (endIndex == startIndex) {
                startIndex++;
            }
            endIndex = startIndex;
        }
        return result;
    }
}
