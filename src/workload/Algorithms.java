package workload;

public class Algorithms {

	/**
	 * Linear algorithm
	 * 
	 * @param n given workload
	 */
	public static void linear(long n) {
		for (long i = 0; i <= n; i++) {
			TestBench.doNothing(i);
			// System.out.println("executing job: " + i);
		}
	}

	/**
	 * Quadratic algorithm
	 * 
	 * @param n given workload
	 */
	public static void quadratic(long n) {
		for (long j = 0; j <= n; j++) {
			for (long i = 0; i <= n; i++) {
				TestBench.doNothing(i);
				// System.out.println("executing job: " + i);
			}
		}
	}

	/**
	 * Cubic algorithm
	 * 
	 * @param n given workload
	 */
	public static void cubic(long n) {
		for (long k = 0; k <= n; k++) {
			for (long j = 0; j <= n; j++) {
				for (long i = 0; i <= n; i++) {
					TestBench.doNothing(i);
					// System.out.println("executing job: " + i);
				}
			}
		}

	}

	/**
	 * Logarithmic algorithm
	 * 
	 * @param n given workload
	 */
	public static void logarithmic(long n) {
		int i = 0;
		while (n > 0) {
			TestBench.doNothing(i);
			i++;
			n = n / 2;
		}
	}

	/**
	 * Factorial algorithm
	 * 
	 * @param n given workload
	 */
	public static long factorial(long n) {
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}

	// Lab 02

	/**
	 * Recursive version of the factorial algorithm
	 * 
	 * @param n given long
	 * @return factorial of the introduced n
	 */
	public static long factorialRec(long n) {
		if (n == 0) {
			return 1;
		} else {
			return (n * factorialRec(n - 1));
		}
	}

	/**
	 * Power algorithm
	 * 
	 * @param n given long
	 * @return power of the introduced n
	 */
	public static long pow(long n) {
		long power = 2;
		long result = 1;
		for (int i = 1; i <= n; i++) {
			TestBench.doNothing(result);
			result *= power;
		}
		return result;
	}

//	/**
//	 * Recursive version of the power algorithm (version 1)
//	 * 
//	 * @param n given long
//	 * @return power of the introduced n
//	 */
//	// the amount of calls to the same method makes the test with n = 40 very time
//	// consuming
//	public static long powRec1(long n) {
//		if (n == 0) {
//			return 1;
//		} else {
//			return powRecl(n - 1) + powRecl(n - 1);
//		}

	/**
	 * Recursive version of the power algorithm (version 1)
	 * 
	 * @param n given long
	 * @return power of the introduced n
	 */
	// the amount of calls to the same method makes the test with n = 40 very time
	// consuming
	public static long powRec1(long n) {
		long result;
		if (n == 0) {
			result = 1;
		} else {
			result = powRec1(n - 1) + powRec1(n - 1);
		}
		TestBench.doNothing(result);
		return result;
	}
	
//	/**
//	 * Recursive version of the power algorithm (version 2; optimized version of the prior)
//	 * 
//	 * @param n given long
//	 * @return power of the introduced n
//	 */
//	public static long powRec2(long n) {
//		if (n == 0) {
//			return 1;
//		} else {
//			return powRec2(n - 1) * 2;
//		}
//	}

	/**
	 * Recursive version of the power algorithm (version 2; optimized version of the
	 * prior)
	 * 
	 * @param n given long
	 * @return power of the introduced n
	 */
	public static long powRec2(long n) {
		long result;
		if (n == 0) {
			result = 1;
		} else {
			result = powRec2(n - 1) * 2;
		}
		TestBench.doNothing(result);
		return result;
	}

//	/**
//	 * Recursive version of the power algorithm (version 3; used with the new
//	 * definition of 2^n)
//	 * 
//	 * @param n given long
//	 * @return power of the introduced n
//	 */
//	public static long powRec3(long n) {
//		if (n == 0) {
//			return 1;
//		} else if (n % 2 == 0) {
//			return powRec3(n / 2) * powRec3(n / 2);
//		} else {
//			return powRec3(n / 2) * powRec3(n / 2) * 2;
//		}
//	}
	
	/**
	 * Recursive version of the power algorithm (version 3; used with the new
	 * definition of 2^n)
	 * 
	 * @param n given long
	 * @return power of the introduced n
	 */
	public static long powRec3(long n) {
		long result;
		if (n == 0) {
			result = 1;
		} else if (n % 2 == 0) {
			result = powRec3(n / 2) * powRec3(n / 2);
		} else {
			result = powRec3(n / 2) * powRec3(n / 2) * 2;
		}
		TestBench.doNothing(result);
		return result;
	}

	/**
	 * Recursive version of the power algorithm (version 4; optimized version of the
	 * prior)
	 * 
	 * @param n given long
	 * @return power of the introduced n
	 */
	public static long powRec4(long n) {
		long result = 0;
		if (n == 0) {
			return 1;
		} else {
			result += powRec4(n / 2);
			result *= result;
			if (n % 2 != 0) {
				result *= 2;
			}
		}
		TestBench.doNothing(result);
		return result;
	}
}
