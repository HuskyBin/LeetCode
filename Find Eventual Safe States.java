/*
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
*/
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> resultList = new ArrayList<>();
        int[] outGoing = new int[graph.length];
        Map<Integer, List<Integer>> inGoingMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            outGoing[i] = graph[i].length;
            for (int j = 0; j < graph[i].length; j++) {
                inGoingMap.computeIfAbsent(graph[i][j], key -> new ArrayList<Integer>()).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (outGoing[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            resultList.add(curNode);
            List<Integer> neighborNodes = inGoingMap.get(curNode);
            if (neighborNodes == null) {
                continue;
            }
            for (int node : neighborNodes) {
                outGoing[node]--;
                if (outGoing[node] == 0) {
                    queue.add(node);
                }
            }
        }
        Collections.sort(resultList);
        return resultList;
    }
}


// dfs
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> resultList = new ArrayList<>();
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                dfs(graph, i, color);
            }
        }
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 2) {
                resultList.add(i);
            }
        }
        return resultList;
    }
    
    private boolean dfs(int[][] graph, int node, int[] color) {
        if (color[node] != 0) {
            return color[node] == 2;
        }
        color[node] = 1;
        for (int nextNode : graph[node]) {
            if (color[nextNode] == 1 || !dfs(graph, nextNode, color)) {
                return false;
            }
        }
        color[node] = 2;
        return true;
    }
}
