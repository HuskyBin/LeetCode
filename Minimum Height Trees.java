/*
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Show Hint 

*/
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 0 || edges == null) {
            return null;
        }

        Set<Integer>[] nodes = new HashSet[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new HashSet<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int first = edges[i][0];
            int second = edges[i][1];
            nodes[first].add(second);
            nodes[second].add(first);
        }

        int result = n;
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].size() <= 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> nextLeaves = new ArrayList<>();
            for (int i : leaves) {
                for (int j : nodes[i]) {
                    nodes[j].remove(i);
                    if (nodes[j].size() == 1) {
                        nextLeaves.add(j);
                    }
                }
            }
            leaves = nextLeaves;
        }
        return leaves;
    }
}
