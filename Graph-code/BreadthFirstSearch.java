

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;
		}
		if (start.getElement().equals(elementToFind)) {
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	
	/**
	 * Find the shortest distance from a node to a destinate string
	 * @param start
	 * @param dest
	 * @return
	 */
	public int shortestDistance(Node start, String dest) {
		if (!graph.containsNode(start)) return -1;
		else if (start.equals(graph.getNode(dest))) return 0;
		Queue<Node> nodeToExplore = new LinkedList<Node>();
		marked.add(start);
		nodeToExplore.add(start);
		Map<Node, Integer> distance = new HashMap<Node, Integer>();
		distance.put(start, 0);
		while (!nodeToExplore.isEmpty()) {
			Node current = nodeToExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					distance.put(neighbor, distance.get(current) + 1);
					if (neighbor.getElement().equals(dest)) {
						return distance.get(neighbor);
					}
					marked.add(neighbor);
					nodeToExplore.add(neighbor);
				}
			}
		}
		return - 1;
	}
}
