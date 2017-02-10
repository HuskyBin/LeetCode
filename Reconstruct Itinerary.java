/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/
public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> resultList = new ArrayList<>();
        if (tickets == null) {
            return resultList;
        }
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] str : tickets) {
            String start = str[0];
            String end = str[1];
            if (map.containsKey(start)) {
                map.get(start).add(end);
            }
            else {
                PriorityQueue<String> newQueue = new PriorityQueue<>();
                newQueue.add(end);
                map.put(start, newQueue);
            }
        }
        
       generatePath("JFK", resultList, map, tickets.length);
       return resultList;
    }
    
    private boolean generatePath(String node, List<String> resultList, Map<String, PriorityQueue<String>> map, int level) {
        if (level == 0) {
            resultList.add(node);
            return true;
        }
        if (!map.containsKey(node)) {
            return false;
        }
        resultList.add(node);
        PriorityQueue<String> nodeQueue = map.get(node);
        List<String> nextNodes = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            nextNodes.add(nodeQueue.poll());
        }
        for (int i = 0; i < nextNodes.size(); i++) {
            map.get(node).clear();
            for (int j = 0; j < nextNodes.size(); j++) {
                if (j == i) continue;
                map.get(node).add(nextNodes.get(j));
            }
            boolean result = generatePath(nextNodes.get(i), resultList, map, level - 1);
            if (result == true) {
                return true;
            }     
        }
        map.get(node).clear();
        for (String nextNode : nextNodes) {
            map.get(node).add(nextNode);    
        }
        resultList.remove(resultList.size() - 1);
        return false;
    }
}
