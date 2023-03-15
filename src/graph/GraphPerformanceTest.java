package graph;

public class GraphPerformanceTest {

	// returns a graph of Integer elements containing n nodes
	// every node is connected with each other by an edge of
	// weight calculated as a random value.

	public static Graph<Integer> initGraph(long n) {
		Graph<Integer> graph = new Graph<Integer>((int) n);
		for (int i = 0; i < n; i++) {
				graph.addNode(i);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				try {
					graph.addEdge((int) (Math.random() + 1), graph.getNodes().get(i).getElement(),
							graph.getNodes().get(j).getElement());
				} catch (Exception e) {
				}
			}
		}
		graph.print();
		return graph;
	}

	// calls to the initGraph(n) method and runs the Dijkstra
	// algorithm on the resulting graph.
	public static void runDijkstra(long n) {
		Graph<Integer> graph = initGraph(n);
		graph.Dijkstra(0);

	}

	// calls to the initGraph(n) method and runs the Floyd
	// algorithm on the resulting graph.
	public static void runFloyd(long n) {
		Graph<Integer> graph = initGraph(n);
		graph.floyd(graph.getSize());
	}

}
