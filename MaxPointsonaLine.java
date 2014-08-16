/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/
public class Solution {
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        
        Map<Double, Integer> slopeMap = new HashMap<>();
        int max = 1;
        for (int i = 0; i < points.length; i++) {
            int duplications = 0;
            slopeMap.clear();
            slopeMap.put((double)Integer.MIN_VALUE, 1);
            for (int j = i + 1; j < points.length; j++) {
                Point firstPoint = points[i];
                Point secondPoint = points[j];
                if (firstPoint.x == secondPoint.x && firstPoint.y == secondPoint.y) {
                    duplications++;
                    continue;
                }
                double slope = (firstPoint.x == secondPoint.x) ? (double)(Integer.MAX_VALUE) : (0.0 + (double)(firstPoint.y - secondPoint.y) / (firstPoint.x - secondPoint.x));
                if (slopeMap.containsKey(slope)) {
                    slopeMap.put(slope, slopeMap.get(slope) + 1);
                }
                else {
                    slopeMap.put(slope, 2);
                }
            }
            for (int temp : slopeMap.values()) {
                max = Math.max(temp + duplications, max);
            }
        }
        return max;
    }
}
