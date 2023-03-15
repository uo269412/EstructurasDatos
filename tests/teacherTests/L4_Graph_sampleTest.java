package teacherTests;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.Graph;

public class L4_Graph_sampleTest {

	@Test
	public void Test_Edit_A() {
		Graph<Character> g1 = new Graph<Character>(3);

		System.out.println("TEST EDIT A BEGINS ***");
		assertEquals(0, g1.getSize());

		try {
			g1.addNode('a');
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}

		assertEquals(1, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Test nodes for nodes not found
		assertEquals(Graph.INDEX_NOT_FOUND, g1.getNode('b'));

		// No repeated nodes allowed
		try {
			g1.addNode('a');
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}

		try {
			g1.addNode('b');
			g1.addNode('c');
		} catch (Exception e) {
			System.out.println("No repeated nodes are allowed" + e);
		}

		assertEquals(3, g1.getSize());
		assertEquals(0, g1.getNode('a'));
		assertEquals(1, g1.getNode('b'));
		assertEquals(2, g1.getNode('c'));

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		// Testing edges
		try {
			assertEquals(false, g1.existsEdge('b', 'd'));
			fail();
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		try {
			assertEquals(false, g1.existsEdge('b', 'c'));
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.addEdge('b', 'c', 5.0);
			assertEquals(true, g1.existsEdge('b', 'c'));
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.addEdge('a', 'b', 2.0);
			assertEquals(true, g1.existsEdge('a', 'b'));
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.addEdge('a', 'c', 1.0);
			assertEquals(true, g1.existsEdge('a', 'c'));
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, true, true }, { false, false, true }, { false, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 1.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.addEdge('c', 'a', 3.0);
			assertEquals(true, g1.existsEdge('a', 'c'));
		} catch (Exception e) {
			System.out.println("Departure or arrival node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, true, true }, { false, false, true }, { true, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 2.0, 1.0 }, { 0.0, 0.0, 5.0 }, { 3.0, 0.0, 0.0 } }, g1.getWeight());

		try {
			g1.removeNode('a');
		} catch (Exception e) {
			System.out.println("Node does not exist" + e);
		}

		assertArrayEquals(new boolean[][] { { false, false, true }, { true, false, true }, { true, false, false } },
				g1.getEdges());
		assertArrayEquals(new double[][] { { 0.0, 0.0, 1.0 }, { 5.0, 0.0, 5.0 }, { 3.0, 0.0, 0.0 } }, g1.getWeight());
	}

	@Test
	public void drainNodes() {
		Graph<Character> g1 = new Graph<Character>(3);
		// Adding the nodes
		try {
			g1.addNode('a');
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		try {
			g1.addNode('b');
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		try {
			g1.addNode('c');
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		// Checking no edges exist
		try {
			assertEquals(g1.existsEdge('a', 'b'), false);
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		try {
			assertEquals(g1.existsEdge('c', 'b'), false);
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		// B node is not a drain node at the moment, both output degree and input degree
		// are zero
		assertEquals(g1.isDrainNode('b'), false);
		// Furthermore, there are no drain node now
		assertEquals(g1.countDrainNodes(), 0);
		// Making the b node a drain node
		try {
			g1.addEdge('a', 'b', 2);
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		// Now input degree = 1; therefore it is a drain node
		assertEquals(g1.isDrainNode('b'), true);
		assertEquals(g1.countDrainNodes(), 1);
		// We increase the output degree to one, b is no longer a drain node
		try {
			g1.addEdge('b', 'c', 4);
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		assertEquals(g1.isDrainNode('b'), false);
		// C is now a drain node, coming from the last added edge
		assertEquals(g1.isDrainNode('c'), true);
		assertEquals(g1.countDrainNodes(), 1);
		//We try adding an edge from c to c
		try {
			g1.addEdge('c', 'c', 3);
		} catch (Exception e) {
			System.out.println("ERROR FOUND DRAIN TEST");
		}
		// C is no longer a drain node. Furthermore there are no more drain nodes
		assertEquals(g1.isDrainNode('c'), false);
		assertEquals(g1.countDrainNodes(), 0);
	}

	@Test
	public void sourceNodes() {
		Graph<Character> g1 = new Graph<Character>(3);
		// Adding the nodes
		try {
			g1.addNode('a');
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		try {
			g1.addNode('b');
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		try {
			g1.addNode('c');
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		// Checking no edges exist
		try {
			assertEquals(g1.existsEdge('a', 'b'), false);
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		try {
			assertEquals(g1.existsEdge('c', 'b'), false);
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		// B node is not a source node at the moment, both output degree and input
		// degree
		// are zero
		assertEquals(g1.isSourceNode('b'), false);
		// Furthermore, there are no source nodes now
		assertEquals(g1.countSourceNodes(), 0);
		// Making the b node a source node
		try {
			g1.addEdge('b', 'a', 2);
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		// Now input degree = 1; therefore it is a source node
		assertEquals(g1.isSourceNode('b'), true);
		assertEquals(g1.countSourceNodes(), 1);
		// We increase the output degree to one, b is no longer a source node
		try {
			g1.addEdge('c', 'b', 4);
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		assertEquals(g1.isSourceNode('b'), false);
		// C is now a source node, coming from the last added edge
		assertEquals(g1.isSourceNode('c'), true);
		assertEquals(g1.countSourceNodes(), 1);
		//We try adding an edge from c to c
		try {
			g1.addEdge('c', 'c', 3);
		} catch (Exception e) {
			System.out.println("ERROR FOUND SOURCE TEST");
		}
		// C is no longer a source node. Furthermore there are no more source nodes
		assertEquals(g1.isSourceNode('c'), false);
		assertEquals(g1.countSourceNodes(), 0);
	}
}
