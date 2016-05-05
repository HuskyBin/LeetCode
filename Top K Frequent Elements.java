
/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
Subscribe to see which companies asked this question
*/
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> (a.getValue() - b.getValue()));
        for (int number : nums) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        while (heap.size() > 0) {
            result.add(heap.poll().getKey());
        }
        return result;
    }
}
