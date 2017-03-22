/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        if (edges == null) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!map.containsKey(edges[i][0])) {
                List<Integer> newList = new ArrayList<>();
                map.put(edges[i][0], newList);
            }
            map.get(edges[i][0]).add(edges[i][1]);
            if (!map.containsKey(edges[i][1])) {
                List<Integer> newList = new ArrayList<>();
                map.put(edges[i][1], newList);
            }
            map.get(edges[i][1]).add(edges[i][0]);
        }
        int result = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == true) {
                continue;
            }
            checkComponent(map, visited, i);
            result ++;
        }
        return result;
    }
    
    private void checkComponent(Map<Integer, List<Integer>> map, boolean[] visited, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            List<Integer> connectNodes = map.getOrDefault(curNode, new ArrayList<Integer>());
            for (int node : connectNodes) {
                if (visited[node] == false) {
                    queue.add(node);
                    visited[node] = true;
                }
            }
        }
    }
}
