/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*/

// Best solution
class Solution {
    public boolean isReflected(int[][] points) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            set.add(Arrays.hashCode(p));
        }
        sum = max + min;
        
        for (int[] p : points) {
            if (!set.contains(Arrays.hashCode(new int[]{sum - p[0], p[1]}))) {
                return false;
            }
        }
        return true;
    }
}

// My solution
class Solution {
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 1) {
            return true;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            Set<Integer> set = map.getOrDefault(points[i][1], new HashSet<Integer>());
            set.add(points[i][0]);
            map.put(points[i][1], set);
        }
        int sum = 0;
        boolean isFirst = true;
        for (int key : map.keySet()) {
            Set<Integer> set = map.get(key);
            List<Integer> list = new ArrayList<>(set);
            if (set.size() == 1) {
                if (isFirst) {
                    isFirst = false;
                    sum = set.iterator().next() * 2;
                }
                else {
                    int curSum = set.iterator().next() * 2;
                    if (curSum != sum) {
                        return false;
                    }
                }
                continue;
            }
            Collections.sort(list);
            for (int i = 0; i <= list.size() / 2; i++) {
                if (isFirst) {
                    sum = list.get(i) + list.get(list.size() - 1 - i);
                    isFirst = false;
                }
                else {
                    int curSum = list.get(i) + list.get(list.size() - 1 - i);
                    if (curSum != sum) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
