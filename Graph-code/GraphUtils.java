

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */
// For all of these, be sure to test your implementation with both directed and undirected graphs.

public class GraphUtils {

	/**
	 * Given a Graph, this method returns the shortest distance (in terms of number of edges) 
	   from the node labeled src to the node labeled dest. 
	   The method should return -1 for any invalid inputs, including: null values for the Graph, src, or dest; 
	   there is no node labeled src or dest in the graph; there is no path from src to dest. 
	   Keep in mind that this method does not just return the distance of any path from src to dest, it must be the shortest path.
	 * @param graph
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null || !graph.containsElement(src) || !graph.containsElement(dest)) return -1;
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		return bfs.shortestDistance(graph.getNode(src), dest);
	}
	
	/**
	 * Given a Graph, this method returns a Set of the values of all nodes within the specified distance 
	   (in terms of number of edges) of the node labeled src, i.e. 
	   for which the minimum number of edges from src to that node is less than or equal to the specified distance. 
	   The value of the node itself should not be in the Set, even if there is an edge from the node to itself. 
	   The method should return null for any invalid inputs, including: null values for the Graph or src; 
	   there is no node labeled src in the graph; distance less than 1. 
	   However, if distance is greater than or equal to 1 and there are no nodes within that distance 
	   (meaning: src is the only node in the graph), the method should return an empty Set.
	 * @param graph
	 * @param src
	 * @param distance
	 * @return
	 */
	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if (graph == null || src == null || distance < 1 || !graph.containsElement(src)) return null;
		HashSet<String> myset = new HashSet<String>();
		if (graph.getNumNodes() == 1) return myset;
		LinkedList<Node> nodeToExplore = new LinkedList<Node>();
		Set<Node> marked = new HashSet<Node>();
		Map<Node, Integer> myMap = new HashMap<Node, Integer>();
		marked.add(graph.getNode(src));
		nodeToExplore.add(graph.getNode(src));
		myMap.put(graph.getNode(src), 0);
		while (!nodeToExplore.isEmpty()) {
			Node current = nodeToExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					myMap.put(neighbor, myMap.get(current) + 1);
					if (myMap.get(neighbor) <= distance) {
						myset.add(neighbor.getElement());
					}
					marked.add(neighbor);
					nodeToExplore.add(neighbor);
				}
			}
		}
		return myset;
	}

	/**
	 * Given a Graph, this method indicates whether the List of node values represents a Hamiltonian Path. 
	   A Hamiltonian Path is a valid path through the graph in which every node in the graph is visited exactly once, 
	   except for the start and end nodes, which are the same, so that it is a cycle. 
	   If the values in the input List represent a Hamiltonian Path, the method should return true, 
	   but the method should return false otherwise, e.g. 

	   if the path is not a cycle, if some nodes are not visited, 
	   if some nodes are visited more than once, 
	   if some values do not have corresponding nodes in the graph, 
	   if the input is not a valid path (i.e., there is a sequence of nodes in the List that are not connected by an edge), etc. 
	   The method should also return false if the input Graph or List is null.
	 * @param g
	 * @param values
	 * @return
	 */
	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if (g == null || values == null || values.isEmpty()) return false;
		// detect if a cycle
		if (!g.getNode(values.get(0)).equals(g.getNode(values.get(values.size() - 1)))) return false;
		ArrayList<Node> mArrayList = new ArrayList<Node>();
		mArrayList.add(g.getNode(values.get(0)));
		// if visted more once and not the start and end nodes
		for (String str : values.subList(1, values.size() - 1)) {
			// not a valid node
			if (!g.containsElement(str)) return false;
			// visited but not the end node
			else if (mArrayList.contains(g.getNode(str)) && !g.getNode(str).equals(g.getNode(values.get(values.size() - 1)))) return false;
			// not connected
			else if (!g.getNodeNeighbors(mArrayList.get(mArrayList.size() - 1)).contains(g.getNode(str))) return false;
			mArrayList.add(g.getNode(str));
		}
		if (mArrayList.size() == g.getNumNodes()) return true;
		return false;
	}
}
