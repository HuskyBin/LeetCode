/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.


*/
import java.util.*;

public class CourseSchedule {


	public static void main(String[] args) {
		int numCourses = 4;
		int[][] prerequisites = {{1,0}, {3,2}, {2,1}};
		CourseSchedule object = new CourseSchedule();
		boolean result = object.canFinish(numCourses, prerequisites);
		System.out.println(result);
	}

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

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
        	return false;
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



        return checkIfValidCourseSchedule(indegreeMap);
    }

    private boolean checkIfValidCourseSchedule(Map<Node, Integer> indegreeMap) {
    	Queue<Node> queue = new LinkedList<>();
    	Map<Node, Boolean> visited = new HashMap<>();
    	for (Map.Entry<Node, Integer> entry : indegreeMap.entrySet()) {
    		Node node = entry.getKey();
    		Integer degree = entry.getValue();
    		if (degree == 0) {
    			queue.offer(node);
    		}
    	}

    	while (queue.size() > 0) {
    		Node topNode = queue.poll();
    		visited.put(topNode, true);
    		for (Node node : topNode.neighbors) {
    			if (visited.containsKey(node)) {
    				return false;
    			}
    			indegreeMap.put(node, indegreeMap.get(node) - 1);
    			if (indegreeMap.get(node) == 0) {
    				queue.offer(node);
    			}
    		}
    	}
    	if (visited.size() != indegreeMap.size()) {
    		return false;
    	}
    	return true;
    }
}
