/*

If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.
*/
class Solution {
    public int pathSum(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int[] result = new int[1];
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int num : nums) {
            int depth = num / 100;
            int position = num / 10 % 10;
            int value = num % 10;
            Map<Integer, Integer> newMap = map.getOrDefault(depth, new HashMap<Integer, Integer>());
            newMap.put(position, value);
            map.put(depth, newMap);
        }
        
        dfsCore(1, 1, map, 0, result);
        return result[0];
    }
    
    private void dfsCore(int depth, int position, Map<Integer, Map<Integer, Integer>> map, int curSum, int[] result) {
        Map<Integer, Integer> posMap = map.get(depth);
        if (!posMap.containsKey(position)) {
            return ;
        }
        int value = posMap.get(position);
        curSum += value;
        if (!map.containsKey(depth + 1) || (!map.get(depth + 1).containsKey(position * 2) && 
                                          !map.get(depth + 1).containsKey(position * 2 - 1))) {
            result[0] += curSum;
            return;
        }
        dfsCore(depth + 1, position * 2 - 1, map, curSum, result);
        dfsCore(depth + 1, position * 2, map, curSum, result);
        return;
    }
}
