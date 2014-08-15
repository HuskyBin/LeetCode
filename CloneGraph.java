/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

//DFS
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        Map<Integer, UndirectedGraphNode> cloneNodeMap = new HashMap<>();
        UndirectedGraphNode cloneNode = cloneGraphCore(node, cloneNodeMap);
        return cloneNode;
    }
    
    public UndirectedGraphNode cloneGraphCore(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> cloneNodeMap) {
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        cloneNodeMap.put(node.label, newNode);
        List<UndirectedGraphNode> newNeighborNodes = new ArrayList<>();
        for (UndirectedGraphNode neighborNode : node.neighbors) {
            if (cloneNodeMap.containsKey(neighborNode.label)) {
                newNeighborNodes.add(cloneNodeMap.get(neighborNode.label));
            }
            else {
                UndirectedGraphNode newNeighbor = cloneGraphCore(neighborNode, cloneNodeMap);
                newNeighborNodes.add(newNeighbor);
            }
        }
        newNode.neighbors = newNeighborNodes;
        return newNode;
    }
}


//BFS
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        Map<Integer, UndirectedGraphNode> cloneNodeMap = new HashMap<>();
        UndirectedGraphNode cloneNode = cloneGraphCore(node, cloneNodeMap);
        return cloneNode;
    }
    
    public UndirectedGraphNode cloneGraphCore(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> cloneNodeMap) {
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        cloneNodeMap.put(node.label, newNode);
        Queue<UndirectedGraphNode> queueNodes = new LinkedList<>();
        queueNodes.offer(node);
        while (queueNodes.size() > 0) {
            UndirectedGraphNode topNode = queueNodes.poll();
            UndirectedGraphNode newCloneNode = cloneNodeMap.get(topNode.label);
            List<UndirectedGraphNode> newNeighborNodes = new ArrayList<>();
            for (UndirectedGraphNode neighborNode : topNode.neighbors) {
                if (cloneNodeMap.containsKey(neighborNode.label)) {
                    newNeighborNodes.add(cloneNodeMap.get(neighborNode.label));
                }
                else {
                    UndirectedGraphNode newNeighborCloneNode = new UndirectedGraphNode(neighborNode.label);
                    cloneNodeMap.put(neighborNode.label, newNeighborCloneNode);
                    newNeighborNodes.add(newNeighborCloneNode);
                    queueNodes.offer(neighborNode);
                }
            }
            newCloneNode.neighbors = newNeighborNodes;
        }
        return newNode;
    }
}
