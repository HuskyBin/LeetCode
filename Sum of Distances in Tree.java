/*

An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: 
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000


*/

/*
Well, another long solution.

Intuition:
What if given a tree, with a certain root 0?
In O(N) we can find sum of distances in tree from root and all other nodes.
Now for all N nodes?
Of course, we can do it N times and solve it in O(N^2).
C++ and Java may get accepted luckly, but it's not what we want.

When we move our root from one node to its connected node, one part of nodes get closer, one the other part get further.
If we know exactly hom many nodes in both parts, we can solve this problem.

With one single traversal in tree, we should get enough information for it and don't need to do it again and again.

Explanation:
0. Let's solve it with node 0 as root.

Initial an array of hashset tree, tree[i] contains all connected nodes to i.
Initial an array count, count[i] counts all nodes in the subtree i.
Initial an array of res, res[i] counts sum of distance in subtree i.

Post order dfs traversal, update count and res:
count[root] = sum(count[i]) + 1
res[root] = sum(res[i]) + sum(count[i])

Pre order dfs traversal, update res:
When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i] nodes get 1 futhur to root.
count[i] = res[root] - count[i] + (n - count[i]);

return res, done.

Time Complexity:
dfs: O(N)
dfs2: O(N)
*/
class Solution {
    int[] res;
    int[] count;
    ArrayList<HashSet<Integer>> tree;
    int n;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<>();
        res = new int[N];
        count = new int[N];
        n = N;
        for (int i = 0; i < N; i++) {
            tree.add(new HashSet<Integer>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        post(0, new HashSet<Integer>());
        preOrder(0, new HashSet<Integer>());
        return res;
    }
    
    private void post(int root, Set<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root)) {
            if (!seen.contains(i)) {
                post(i, seen);
                count[root] += count[i];
                res[root] += res[i] + count[i];
            }
        }
        count[root]++;
    }
    
    private void preOrder(int root, Set<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root)) {
            if (seen.contains(i)) {
                continue;
            }
            res[i] = res[root] - count[i] + n - count[i];
            preOrder(i, seen);
        }
    }
}
