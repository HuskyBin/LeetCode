/*
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (graph == null) {
            return resultList;
        }
        
        boolean[] visited = new boolean[graph.length];
        List<Integer> path = new ArrayList<>();
        findAllPath(graph, path, resultList, visited, 0);
        return resultList;
    }
    
    private void findAllPath(int[][] graph, List<Integer> path, List<List<Integer>> resultList, boolean[] visited, int node) {
        if (node == graph.length - 1) {
            List<Integer> onePath = new ArrayList<>(path);
            onePath.add(node);
            resultList.add(onePath);
            return;
        }
        visited[node] = true;
        path.add(node);
        for (int nextNode : graph[node]) {
            if (!visited[nextNode]) {
                findAllPath(graph, path, resultList, visited, nextNode);
            }
        }
        path.remove(path.size() - 1);
        visited[node] = false;
    }
}
