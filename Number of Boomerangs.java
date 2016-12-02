/*
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
*/
public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if (points == null) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dis = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (!map.containsKey(dis)) {
                    map.put(dis, 0);
                }
                map.put(dis, map.get(dis) + 1);
            }
            for (Integer k : map.keySet()) {
                result += map.get(k) * (map.get(k) - 1);
            }
        }
        return result;
    }
}
