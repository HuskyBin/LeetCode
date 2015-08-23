/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
*/



public class Solution {


	private class Node {
		public int value;
		public List<Node> neighbors;

		public Node(int value) {
			this.value = value;
			neighbors = new ArrayList<>();
		}
	}


	private void addNeighborNode(int[][] prerequisites, Map<Integer, Node> graphNode, Node node, int i, Set<Node> nodeList) {
		for (int j = 1; j < prerequisites[i].length; j++) {
        	int preCoursen = prerequisites[i][j];
        	if (graphNode.containsKey(preCoursen)) {
        		Node preNode = graphNode.get(preCoursen);
        		node.neighbors.add(preNode);
        		nodeList.add(preNode);
        	}
        	else {
        		Node newPreNode = new Node(preCoursen);
        		node.neighbors.add(newPreNode);
        		graphNode.put(preCoursen, newPreNode);
        		nodeList.add(newPreNode);
        	}
        }
	}

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] defaultArray = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            defaultArray[i] = i;
        }
        if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0) {
            return defaultArray;
        }

        Set<Node> nodeList = new HashSet<>();
        Map<Integer, Node> graphNode = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
        	int course = prerequisites[i][0];
        	if (graphNode.containsKey(course)) {
        		Node node = graphNode.get(course);
        		addNeighborNode(prerequisites, graphNode, node, i, nodeList);
        		nodeList.add(node);
        	}
        	else {
        		Node newNode = new Node(course);
        		addNeighborNode(prerequisites, graphNode, newNode, i, nodeList);
        		nodeList.add(newNode);
        		graphNode.put(course, newNode);
        	}
        }

        Map<Node, Integer> indegreeMap = new HashMap<>();

        for (Node node : nodeList) {
        	indegreeMap.put(node, 0);
        }
        for (Node node : nodeList) {
        	// System.out.println("Node is: " + node.value);
        	for (Node neighborNode : node.neighbors) {
        		// System.out.println("neighbors node is: " + neighborNode.value);
        		indegreeMap.put(neighborNode, indegreeMap.get(neighborNode) + 1);
        	}
        }



        List<Integer> resultList = checkIfValidCourseSchedule(indegreeMap);
        if (resultList == null) {
            return new int[0];
        }
        Collections.reverse(resultList);
        int[] array = new int[numCourses];
        for (int i = 0; i < resultList.size(); i++) {
            array[i] = resultList.get(i);
        }
        Set<Integer> appearedNum = new HashSet<>();
        for (int i : array) {
            appearedNum.add(i);
        }
        int offset = 1;
        for (int i = 0; i < numCourses; i++) {
            if (!appearedNum.contains(i)) {
                array[resultList.size() - 1 + offset] = i;
                offset++;
            }
        }
        return array;
    }

    private List<Integer> checkIfValidCourseSchedule(Map<Node, Integer> indegreeMap) {
    	Queue<Node> queue = new LinkedList<>();
    	Map<Node, Boolean> visited = new HashMap<>();
    	List<Integer> resultList = new ArrayList<>();
    	for (Map.Entry<Node, Integer> entry : indegreeMap.entrySet()) {
    		Node node = entry.getKey();
    		Integer degree = entry.getValue();
    		if (degree == 0) {
    			queue.offer(node);
    		}
    	}

    	while (queue.size() > 0) {
    		Node topNode = queue.poll();
    		resultList.add(topNode.value);
    		visited.put(topNode, true);
    		for (Node node : topNode.neighbors) {
    			if (visited.containsKey(node)) {
    				return null;
    			}
    			indegreeMap.put(node, indegreeMap.get(node) - 1);
    			if (indegreeMap.get(node) == 0) {
    				queue.offer(node);
    			}
    		}
    	}
    	if (visited.size() != indegreeMap.size()) {
    		return null;
    	}
    	return resultList;
    }
}
