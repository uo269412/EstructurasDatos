package workload;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class TestBench {

	public static final int SLEEP_TIME = 2;

	public static void main(String args[]) {
//		TestBench.test("logarithmic4.txt", 3, 1, 100);
		
//		TestBench.test(".01_Graph_Floyd.txt", 3, 100, 300, "graph.GraphPerformanceTest", "runFloyd");
//		TestBench.test("02_Graph_Dijkstra.txt", 3, 100, 300, "graph.GraphPerformanceTest", "runDijkstra");
		TestBench.test("03_Graph_Build.txt", 3, 100, 300, "graph.GraphPerformanceTest", "initGraph");
	}

//	public static void test(long n) {
//		long executionTime = System.currentTimeMillis();
//		Algorithms.linear(n);
//		System.out.println(System.currentTimeMillis() - executionTime);
//	}

//	/**
//	 * This method measures execution times, recording them
//	 * 
//	 * @param outputFileName of the file that will include the times
//	 * @param startN         start of the range for the workload
//	 * @param endN           end of the range for the workload
//	 */
//	public static void test(String outputFileName, int startN, int endN) {
//		FileWriter file = null;
//		PrintWriter pw = null;
//		try {
//			file = new FileWriter(outputFileName);
//			pw = new PrintWriter(file);
//			for (int i = startN; i <= endN; i++) {
//				long executionTime = System.currentTimeMillis();
//				Algorithms.linear(i);
//				pw.println(System.currentTimeMillis() - executionTime);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (file != null) {
//					file.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}

	/**
	 * This method measures execution times, recording the average time of the
	 * samples
	 * 
	 * @param outputFileName of the file that will include the times
	 * @param startN         start of the range for the workload
	 * @param endN           end of the range for the workload
	 * @param samples        indicates the number of times the experiment must be
	 *                       repeated
	 */
	public static void test(String outputFileName, int samples, int startN, int endN, String className, String methodName) {
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter(outputFileName);
			pw = new PrintWriter(file);
			for (int i = startN; i <= endN; i++) {
				long combinedTime = 0;
				for (int j = 0; j <= samples; j++) {
					long startTime = System.currentTimeMillis();
//					Algorithms.logarithmic(i);
					testAlgorithm(className, methodName, i);
					combinedTime += System.currentTimeMillis() - startTime;
				}
				pw.println(combinedTime / samples);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

//	/**
//	 * Creates a new object of a class at execution time, invoking one of its method
//	 * @param className name of the class to be created
//	 * @param methodName name of the method we want to use
//	 * @param n workload for the algorithms
//	 * @throws Exception
//	 */
//	public static void testAlgorithm(String className, String methodName, long n) throws Exception {
//			Object algorithm = Class.forName(className).newInstance();
//			Method method = algorithm.getClass().getDeclaredMethod(methodName, new Class<?>[0]);
//			method.invoke(algorithm, n);
//	}

	/**
	 * Creates a new object of a class at execution time, invoking one of its method
	 * 
	 * @param className  name of the class to be created
	 * @param methodName name of the method we want to use
	 * @param n          workload for the algorithms
	 * @throws Exception
	 */
	public static void testAlgorithm(String className, String methodName, long n) throws Exception {
		Class<?> myClass = null;
		Object myObject = null;

		// Loads the class dynamically using reflection
		myClass = Class.forName(className);
		myObject = myClass.newInstance();

		// Gets a method instance
		Class<?>[] params = new Class[1];
		params[0] = Long.TYPE;

		Method m = myClass.getMethod(methodName, params);

		// Calls the method in java using reflection
		m.invoke(myObject, n);
	}

	/**
	 * Slows down the execution
	 * 
	 * @param i
	 */
	public static void doNothing(long i) {
		System.out.println("Doing nothing at iteration... (" + i + ")");
		long endTime = System.currentTimeMillis() + SLEEP_TIME;
		while (System.currentTimeMillis() < endTime) {
			// do nothing
		}
	}
}
