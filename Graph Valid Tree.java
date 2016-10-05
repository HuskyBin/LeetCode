/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (queue.size() > 0) {
            int peek = queue.poll();
            if (visited[peek] == true) {
                return false;
            }
            visited[peek] = true;
            for (int i : map.get(peek)) {
                if (visited[i] == false) {
                    queue.add(i);
                }
            }
        }
        
        for (boolean i : visited) {
            if (i == false) {
                return false;
            }
        }
        return true;
    }
}
