package graph;

import org.junit.Test;

import workload.TestBench;

public class GraphPerformanceTestTester {

	@Test
	public void test() {
		TestBench.test("01_Graph_Floyd.txt", 3, 100, 300, "GraphPerformanceTest", "runFloyd");
		TestBench.test("02_Graph_Dijkstra.txt", 3, 100, 300, "GraphPerformanceTest", "runDijkstra");
		TestBench.test("03_Graph_Build.txt", 3, 100, 300, "GraphPerformanceTest", "initGraph");
	}

}
