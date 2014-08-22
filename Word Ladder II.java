/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class Solution {
    
    public List<List<String>> resultList = new ArrayList<>();
    
    public void findPath(Node node, List<String> curList, String start) {
        if (node.str.equals(start)) {
            List<String> copyList = new ArrayList<>(curList);
            resultList.add(copyList);
            return;
        }
        for (Node n : node.preNode) {
            curList.add(0, n.str);
            findPath(n, curList, start);
            curList.remove(0);
        }
    }
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() != end.length()) {
            List<List<String>> emptyList = new ArrayList<>();
            return emptyList;
        }
        Map<String, Node> nodeMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(0, start);
        queue.offer(node);
        nodeMap.put(start, node);
        Node endNode = null;
        boolean stop = false;
        while (queue.size() > 0 && stop != true) {
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                node = queue.poll();
                for (int i = 0; i < node.str.length(); i++) {
                    StringBuilder sb = new StringBuilder(node.str);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        if (dict.contains(sb.toString())) {
                            if (nodeMap.containsKey(sb.toString())) {
                                Node newNode = nodeMap.get(sb.toString());
                                if (newNode.level == node.level + 1) {
                                    newNode.preNode.add(node);
                                }
                            }
                            else {
                                Node newNode = new Node(node.level + 1, sb.toString());
                                newNode.preNode.add(node);
                                queue.add(newNode);
                                nodeMap.put(sb.toString(), newNode);
                                if (sb.toString().equals(end)) {
                                    endNode = newNode;
                                    stop = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (endNode != null) {
            List<String> curList = new ArrayList<>();
            curList.add(end);
            findPath(endNode, curList, start);
        }
        return resultList;
    }
}

class Node {
    public int level;
    public String str;

    public List<Node> preNode;

    public Node(int level, String str) {
        this.level = level;
        this.str = str;
        preNode = new ArrayList<>();
    }
}
