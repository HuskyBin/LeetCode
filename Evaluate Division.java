/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || values == null || queries == null) {
        	return new double[0];
        }
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Map<String, Double>> cost = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
        	graph.computeIfAbsent(equations[i][0], key -> new ArrayList<String>())
        		.add(equations[i][1]);
        	graph.computeIfAbsent(equations[i][1], key -> new ArrayList<String>())
        		.add(equations[i][0]);
        	cost.computeIfAbsent(equations[i][0], key -> new HashMap<String, Double>())
        		.put(equations[i][1], values[i]);
        	cost.computeIfAbsent(equations[i][1], key -> new HashMap<String, Double>())
        		.put(equations[i][0], 1.0 / values[i]);	
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            boolean isFound = false;
        	String[] query = queries[i];
        	if (!cost.containsKey(query[0]) || !cost.containsKey(query[1])) {
        		result[i] = -1.0;
        		continue;
        	}
        	if (query[0].equals(query[1])) {
        		result[i] = 1.0;
        		continue;
        	}
        	Queue<Pair> queue = new LinkedList<>();
        	queue.add(new Pair(query[0], 1.0));
        	Set<String> visited = new HashSet<>();
        	visited.add(query[0]);
        	while (!queue.isEmpty()) {
        		Pair curPair = queue.poll();
        		List<String> nextNodes = graph.get(curPair.node);
        		for (String nextNode : nextNodes) {
                    if (visited.contains(nextNode)) {
                        continue;
                    }
        			double newCost = curPair.curCost * cost.get(curPair.node).get(nextNode);
        			if (nextNode.equals(query[1])) {
        				result[i] = newCost;
                        isFound = true;
        				break;
        			}
        			visited.add(nextNode);
        			queue.add(new Pair(nextNode, newCost));
        		}
        	}
            if (!isFound) {
                result[i] = -1.0;
            }
        }
       	return result;
    }

    private static class Pair {
        public String node;
        public double curCost;

        public Pair(String node, double curCost) {
        	this.node = node;
        	this.curCost = curCost;
        }
    }
}
