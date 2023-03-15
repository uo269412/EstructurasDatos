package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
	ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;
	public final static int INDEX_NOT_FOUND = -1;
	public final static double INFINITE = Double.MAX_VALUE;
	public final static int EMPTY = -1;
	protected double[][] A;
	protected int[][] P;
	protected double[] D;
	protected int[] PD;

	/**
	 * Constructor of the graph class
	 * 
	 * @param n size of the graph
	 */
	public Graph(int n) {
		if (n >= 0) {
			nodes = new ArrayList<GraphNode<T>>();
			edges = new boolean[n][n];
			weight = new double[n][n];
			A = new double[n][n];
			P = new int[n][n];
			D = new double[n];
			PD = new int[n];
		}
	}

	/**
	 * Returns the nodes of the graph
	 * 
	 * @return nodes of the graph
	 */
	public ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	/**
	 * Returns the edges of the graph
	 * 
	 * @return edges of the graph
	 */
	public boolean[][] getEdges() {
		return edges;
	}

	/**
	 * Returns the weight of the graph (the cost of such edges)
	 * 
	 * @return weight of the graph
	 */
	public double[][] getWeight() {
		return weight;
	}

	/**
	 * Returns the index of the node that has such element in the graph
	 * 
	 * @param element we want to search in the list
	 * @return position of such element in the list
	 */
	public int getNode(T element) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getElement().equals(element)) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the size of the graph
	 * 
	 * @return integer size of the graph
	 */
	public int getSize() {
		return nodes.size();
	}

	/**
	 * Adds a node to the graph
	 * 
	 * @param element to be added
	 * @throws Exception if the element is already added on the graph
	 */
	public void addNode(T element) {
		if (getNode(element) == INDEX_NOT_FOUND) {
			nodes.add(new GraphNode<T>(element));
			int locationNode = getNode(element);
			for (int i = 0; i < nodes.size(); i++) {
				edges[locationNode][i] = false;
				edges[i][locationNode] = false;
				weight[locationNode][i] = 0;
				weight[i][locationNode] = 0;
			}
		} else {
			throw new RuntimeException("Such node doesn't exist");
		}
	}

	/**
	 * Checks if such edge exists in the graph
	 * 
	 * @param element of the node which we want to check if it's the origin of such
	 *                edge
	 * @param element of the node which we want to check if it's the destination of
	 *                such edge
	 * @return boolean value; true if it exists, false if it not
	 * @throws Exception if the destination or the origin node of such edge doesn't
	 *                   exist
	 */
	public boolean existsEdge(T origin, T dest) {
		if (getNode(origin) == INDEX_NOT_FOUND || getNode(dest) == INDEX_NOT_FOUND) {
			throw new RuntimeException("Such nodes do not exist");
		} else {
			return edges[getNode(origin)][getNode(dest)];
		}
	}

	/**
	 * Adds an edge to the graph
	 * 
	 * @param element of the node we want to make the origin of such edge
	 * @param element of the node we want to make the destination of such edge
	 * @param weight  of the edge we want to add
	 * @throws Exception if such edge already exists
	 */
	public void addEdge(T origin, T dest, double weight) {
		if (existsEdge(origin, dest)) {
			throw new RuntimeException("Such edge already exists");
		} else {
			int originLocation = getNode(origin);
			int endLocation = getNode(dest);
			edges[originLocation][endLocation] = true;
			this.weight[originLocation][endLocation] = weight;
		}
	}

	/**
	 * Removes a node from the graph
	 * 
	 * @param element of the node we want to remove from the graph
	 * @throws Exception if such node doesn't exist
	 */
	public void removeNode(T element) throws Exception {
		if (getNode(element) == INDEX_NOT_FOUND) {
			throw new RuntimeException("Such node doesn't exist");
		}
		int nodeToBeRemoved = getNode(element);
		for (int i = 0; i < nodes.size() - 1; i++) {
			edges[nodeToBeRemoved][i] = edges[nodes.size() - 1][i];
			edges[i][nodeToBeRemoved] = edges[i][nodes.size() - 1];
			weight[nodeToBeRemoved][i] = weight[nodes.size() - 1][i];
			weight[i][nodeToBeRemoved] = weight[i][nodes.size() - 1];
		}
		edges[nodeToBeRemoved][nodeToBeRemoved] = edges[nodes.size() - 1][nodes.size() - 1];
		weight[nodeToBeRemoved][nodeToBeRemoved] = weight[nodes.size() - 1][nodes.size() - 1];
		nodes.set(nodeToBeRemoved, nodes.get(nodes.size() - 1));
		nodes.remove(nodes.size() - 1);

	}

	/**
	 * Removes the edge from the graph
	 * 
	 * @param element of the node we want to remove, being the origin of such edge
	 * @param element of the node we want to remove, being the destination of such
	 *                edge
	 * @throws Exception if such edge doesn't exist
	 */
	public void removeEdge(T origin, T dest) throws Exception {
		if (existsEdge(origin, dest)) {
			int originLocation = getNode(origin);
			int endLocation = getNode(dest);
			edges[originLocation][endLocation] = false;
			weight[originLocation][endLocation] = 0;
		} else {
			throw new RuntimeException("Such nodes or edges don't exist");

		}
	}

	/**
	 * Prints the graph in the console
	 */
	public void print() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).print();
		}
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				System.out.print(edges[i][j] + "(");
				System.out.print(weight[i][j] + ")");
			}
			System.out.println();
		}
	}

	/**
	 * Checks if a node is a drain node or not
	 * 
	 * @param element that's in the node
	 * @return boolean condition satisfying if the node is drain or not
	 */
	public boolean isDrainNode(T element) {
		int position = getNode(element);
		boolean condition = false;
		for (int i = 0; i < nodes.size(); i++) {
			if (edges[position][i] || (i == position && edges[i][position])) {
				return false;
			}
			if (edges[i][position]) {
				condition = true;
			}
		}
		return condition;
	}

	/**
	 * Checks if a node is a source node or not
	 * 
	 * @param element that's in the node
	 * @return boolean condition satisfying if the node is source or not
	 */
	public boolean isSourceNode(T element) {
		int position = getNode(element);
		boolean condition = false;
		for (int i = 0; i < nodes.size(); i++) {
			if (edges[position][i]) {
				condition = true;
			}
			if (edges[i][position] == true || (i == position && edges[i][position])) {
				return false;
			}
		}
		return condition;
	}

	/**
	 * Counts the number of drain nodes in the graph
	 * 
	 * @return number of drain nodes
	 */
	public int countDrainNodes() {
		int counter = 0;
		for (GraphNode<T> node : nodes) {
			if (isDrainNode(node.getElement())) {
				counter++;
			}
		}
		return counter;

	}

	/**
	 * Counts the number of source nodes in the graph
	 * 
	 * @return number of source nodes
	 */
	public int countSourceNodes() {
		int counter = 0;
		for (GraphNode<T> node : nodes) {
			if (isSourceNode(node.getElement())) {
				counter++;
			}
		}
		return counter;

	}

	/**
	 * Sets all the elements to unvisited to then traverse from one node to all the
	 * accessible ones (using the depth-first search)
	 * 
	 * @param element contained in the starting node
	 * @return string containing all the nodes that were traversed or null if the
	 *         node doesn't exist
	 */
	public String traverseGraphDF(T element) {
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		int v = getNode(element);
		if (v != INDEX_NOT_FOUND) {
			return DFPrint(v);
		}
		return null;
	}

	/**
	 * Graph navigation from one node to the accessible ones using the depth first
	 * search method
	 * 
	 * @param int that corresponds to the position of such node with respect to the
	 *        list where it's contained
	 * @return string containing all the nodes that were traversed
	 */
	private String DFPrint(int v) {
		nodes.get(v).setVisited(true);
		String aux = nodes.get(v).getElement().toString();
		aux += "-";
		for (int i = 0; i < getSize(); i++) {
			if (edges[v][i] && nodes.get(i).isVisited() == false) {
				aux += DFPrint(i);
			}
		}
		return aux;
	}

	/**
	 * Returns the cost matrix used in floyd
	 * 
	 * @return cost matrix used in floyd
	 */
	public double[][] getA() {
		return A;

	}

	/**
	 * Returns the pathway matrix used in floyd
	 * 
	 * @return pathway matrix used in floyd
	 */
	public int[][] getP() {
		return P;

	}

	/**
	 * Sets the initial values for the A and P vectors (initialization for floyd)
	 */
	public void initsFloyd() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (edges[i][j]) {
					A[i][j] = weight[i][j];
				} else {
					A[i][j] = INFINITE;
				}
				P[i][j] = EMPTY;
				if (i == j) {
					A[i][j] = 0;
				}
			}
		}
	}

	/**
	 * Floyd method
	 * 
	 * @param number of floyd interactions we want to do
	 */
	public void floyd(int An) {
		initsFloyd();
		for (int k = 0; k < An; k++) {
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j];
						P[i][j] = k;
					}
				}
			}
		}
	}

	/**
	 * Returns a string with the shortest path from one node to another
	 * 
	 * @param departure starting node
	 * @param arrival   arrival node
	 * @return string with the shortest path
	 * @throws Exception
	 */
	public String printFloydPath(T departure, T arrival) {
		if (getNode(departure) == INDEX_NOT_FOUND || getNode(arrival) == INDEX_NOT_FOUND) {
			throw new RuntimeException("Such nodes do not exist");
		}
		return FPprint(departure, arrival);

	}

	/**
	 * Returns a string with the shortest path from one node to another
	 * 
	 * @param departure starting node
	 * @param arrival   arrival node
	 * @return string with the shortest path
	 */
	private String FPprint(T departure, T arrival) {
		int index = P[getNode(departure)][getNode(arrival)];
		if (index == INDEX_NOT_FOUND) {
			return "";
		}
		String element = nodes.get(index).getElement().toString();
		return FPprint(departure, nodes.get(index).getElement()) + element;
	}

	/**
	 * Returns the array of costs used in Dijkstra
	 * 
	 * @return cost matrix
	 */
	public double[][] getD() {
		double[][] aux = new double[1][D.length];
		aux[0] = D;
		return aux;
	}

	/**
	 * Returns the Dijkstra pathway
	 * 
	 * @return pathway matrix
	 */
	public int[] getPD() {
		return PD;
	}

	/**
	 * Initializes Dijkstra, setting the proper values on the D and PD matrix (cost
	 * and pathway)
	 */
	public void initDijkstra(T departureNode) {
		if (departureNode == null || getNode(departureNode) == INDEX_NOT_FOUND) {
			throw new RuntimeException("Such node doesn't exist");
		}
		int index = getNode(departureNode);
		{
			for (int j = 0; j < getSize(); j++) {
				if (edges[index][j]) {
					D[j] = weight[index][j];
					PD[j] = index;
				} else {
					D[j] = INFINITE;
					PD[j] = EMPTY;
				}

			}
		}
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		nodes.get(getNode(departureNode)).setVisited(true);
	}

	/**
	 * Quadratic version of the Dijkstra algorithm
	 * 
	 * @param departure node where Dijkstra starts
	 */
	public void Dijkstra(T departure) {
		initDijkstra(departure);
		int candidate;
		for (int p = 1; p < getSize(); p++) {
			double minimum = INFINITE;
			candidate = INDEX_NOT_FOUND;
			for (int i = 0; i < getSize(); i++) {
				if (D[i] < minimum && !nodes.get(i).isVisited() && i != getNode(departure)) {
					minimum = D[i];
					candidate = i;
				}
			}
			if (candidate != INDEX_NOT_FOUND) {
				nodes.get(candidate).setVisited(true);
				for (int i = 0; i < getSize(); i++) {
					if (D[candidate] + weight[candidate][i] < D[i] && edges[candidate][i]) {
						D[i] = D[candidate] + weight[candidate][i];
						PD[i] = candidate;
					}
				}
			}
		}
	}

	/**
	 * Cubic version of the Dijkstra algorithm
	 * 
	 * @param departure node where Dijkstra starts
	 */
	public void DijkstraO3(T departure) {
		initDijkstra(departure);
		int candidate;
		for (int p = 1; p < getSize(); p++) {
			double minimum = INFINITE;
			candidate = INDEX_NOT_FOUND;
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					if (weight[i][j] < minimum && weight[i][j] > 0 && nodes.get(i).isVisited()
							&& !nodes.get(j).isVisited() && i != j) {
						minimum = D[j];
						candidate = j;
					}
				}
			}
			if (candidate != INDEX_NOT_FOUND) {
				nodes.get(candidate).setVisited(true);
				for (int i = 0; i < getSize(); i++) {
					if (D[candidate] + weight[candidate][i] < D[i] && edges[candidate][i]) {
						D[i] = D[candidate] + weight[candidate][i];
						PD[i] = candidate;
					}
				}
			}
		}
	}

	/**
	 * Prints the elements in an order such that the nearest nodes are printed
	 * first, then the other level nodes (breadth first search and using a fifo)
	 * 
	 * @param element we want to start printing with
	 * @return string with the nodes in such traversal direction
	 */
	public String BFPrint(T element) {
		if (getNode(element) == INDEX_NOT_FOUND) {
			return null;
		}
		initializeNodes();
		String string = element.toString() + "-";
		nodes.get(getNode(element)).setVisited(true);
		List<GraphNode<T>> list = new ArrayList<GraphNode<T>>();
		list.add(nodes.get(getNode(element)));
		while (!list.isEmpty()) {
			for (int i = 0; i < edges.length; i++) {
				T elementToProcess = list.get(0).getElement();
				try {
					if (existsEdge(elementToProcess, nodes.get(i).getElement()) && !nodes.get(i).isVisited()) {
						list.add(nodes.get(i));
						nodes.get(i).setVisited(true);
						string += nodes.get(i).getElement().toString() + "-";
					}
				} catch (Exception e) {
				}
			}
			list.remove(0);
		}
		return string;
	}

	/**
	 * Sets all nodes to visited == false
	 */
	public void initializeNodes() {
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
	}

	/**
	 * Calculates the center of the graph taking into account the costs of the paths
	 * to get to such node
	 * 
	 * @return element of the node which is the center of the graph
	 */
	public T getCenter() {
		floyd(getSize());

		double[] cost = new double[getSize()];
		for (int j = 0; j < A.length - 1; j++) {
			for (int i = 0; i < A.length - 1; i++) {
				if (A[i][j] != INFINITE) {
					cost[i] += A[i][j];
				}
			}
		}
		double maximum = INFINITE;
		int index = -1;
		for (int k = 0; k < cost.length; k++) {
			if (cost[k] < maximum) {
				maximum = cost[k];
				index = k;
			}
		}
		return nodes.get(index).getElement();
	}

	/**
	 * Calculates the shortest path from one node to another
	 * 
	 * @param element of the origin node we want to calculate the shortest path
	 * @param element of the destination node we want to calculate the shortest path
	 * @return int that represents the number of traversed edges
	 */
	public int shortestPathLength(T origin, T destination) {
		floydPathLength(getSize());
		return (int) A[getNode(origin)][getNode(destination)];
	}

	/**
	 * Floyd method used to calculate the shortest path length, replacing the A
	 * matrix which stored costs into a matrix that stores the number of traversed
	 * edges
	 * 
	 * @param number of floyd interactions we want to do
	 */
	public void floydPathLength(int An) {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (edges[i][j]) {
					A[i][j] = 1;
				} else {
					A[i][j] = INFINITE;
				}
			}
		}
		for (int k = 0; k < An; k++) {
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j];
					}
				}
			}
		}
	}
}
