package workload;

import static org.junit.Assert.*;

import org.junit.Test;

import workload.Algorithms;

public class AlgorithmsTest {

	@Test
	public void testFactorial() {
		assertEquals(720, Algorithms.factorial(6));
		assertEquals(720, Algorithms.factorialRec(6));
	}

	@Test
	public void testPower() {
		assertEquals(Algorithms.pow(40), 1099511627776L);
//		assertEquals(Algorithms.powRecl(40), 1099511627776L);
		assertEquals(Algorithms.powRec2(40), 1099511627776L);
		assertEquals(Algorithms.powRec3(40), 1099511627776L);
		assertEquals(Algorithms.powRec4(40), 1099511627776L);
	}
}
