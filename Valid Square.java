/*
这道题给了我们四个点，让我们验证这四个点是否能组成一个正方形，刚开始博主考虑的方法是想判断四个角是否是直角，但即便四个角都是直角，
也不能说明一定就是正方形，还有可能是矩形。还得判断各边是否相等。其实我们可以仅通过边的关系的来判断是否是正方形，
根据初中几何的知识我们知道正方形的四条边相等，两条对角线相等，满足这两个条件的四边形一定是正方形。那么这样就好办了，我们只需要对四个点，
两两之间算距离，如果计算出某两个点之间距离为0，说明两点重合了，直接返回false，如果不为0，那么我们就建立距离和其出现次数之间的映射，
最后如果我们只得到了两个不同的距离长度，那么就说明是正方形了，参见代码如下：
*/
/*
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
*/
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Map<Integer, Integer> map = new HashMap<>();
        int a12 = dis(p1, p2);
        int a13 = dis(p1, p3);
        int a14 = dis(p1, p4);
        int a23 = dis(p2, p3);
        int a24 = dis(p2, p4);
        int a34 = dis(p3, p4);
        if (a12 == 0 || a13 == 0 || a14 == 0 || a23 == 0 || a24 == 0 || a34 == 0) {
            return false;
        }
        map.put(a12, map.getOrDefault(a12, 0) + 1);
        map.put(a13, map.getOrDefault(a13, 0) + 1);
        map.put(a14, map.getOrDefault(a14, 0) + 1);
        map.put(a23, map.getOrDefault(a23, 0) + 1);
        map.put(a24, map.getOrDefault(a24, 0) + 1);
        map.put(a34, map.getOrDefault(a34, 0) + 1);
        if (map.size() == 2 && (map.get(a12) == 2 || map.get(a12) == 4)) {
            return true;
        }
        return false;
    }
    
    private int dis(int[] p1 , int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
