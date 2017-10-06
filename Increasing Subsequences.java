/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

*/
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> resultList = new HashSet<List<Integer>>();
        if (nums == null) {
            return new ArrayList<List<Integer>>();
        }
        List<Integer> candidate = new ArrayList<>();
        findCore(nums, 0, resultList, candidate);
        List rest = new ArrayList(resultList);
        return rest;
    }
    
    private void findCore(int[] nums, int index, Set<List<Integer>> resultList, List<Integer> candidate) {
        for (int i = index; i < nums.length; i++) {
            if (candidate.size() > 0 && nums[i] < candidate.get(candidate.size() - 1)) {
                continue;
            }
            candidate.add(nums[i]);
            if (candidate.size() >= 2) {
                resultList.add(new ArrayList<Integer>(candidate));   
            }
            findCore(nums, i + 1, resultList, candidate);
            candidate.remove(candidate.size() - 1);
        }
    }
}
