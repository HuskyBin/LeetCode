/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
*/
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[][] costArr = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            graph.computeIfAbsent(flights[i][0], key -> new ArrayList<>()).add(flights[i][1]);
            costArr[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        int result = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        int curStop = 0;
        int[] srcCost = new int[2];
        srcCost[0] = src;
        srcCost[1] = 0;
        queue.add(srcCost);
        Map<Integer, Integer> visitedCost = new HashMap<>();
        visitedCost.put(src, 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curCost = queue.poll();
                List<Integer> nextStops = graph.get(curCost[0]);
                if (nextStops == null) {
                    continue;
                }
                for (int nextStop : nextStops) {
                    int cost = curCost[1] + costArr[curCost[0]][nextStop];
                    if (visitedCost.containsKey(nextStop) && visitedCost.get(nextStop) <= cost) {
                        continue;
                    }
                    int[] nextCost = {nextStop, cost};
                    queue.add(nextCost);
                    if (nextStop == dst) {
                        result = Math.min(result, cost);
                    }
                    visitedCost.put(nextStop, cost);
                }
            }
            curStop++;
            if (curStop > K) {
                break;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
