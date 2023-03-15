package hash;

import static org.junit.Assert.*;

import org.junit.Test;

import hash.HashTable;

public class HashTableTests {

	@Test
	public void testHashingFunctionIntegers() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));

		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(2, b.f(7, 0));
		assertEquals(3, b.f(7, 1));
		assertEquals(1, b.f(7, 2));
		assertEquals(1, b.f(7, 3));

	}

	@Test
	public void testHashingFunctionChars() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(0, a.f('A', 0));
		assertEquals(1, a.f('A', 1));
		assertEquals(2, a.f('A', 2));
		assertEquals(3, a.f('A', 3));

		HashTable<Character> b = new HashTable<Character>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(0, b.f('A', 0));
		assertEquals(1, b.f('A', 1));
		assertEquals(4, b.f('A', 2));
		assertEquals(4, b.f('A', 3));
	}

	@Test
	public void testToString() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(a.toString(),
				"[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (0) = null - [4] (0) = null - ");
	}

	@Test
	public void addAndSearch() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);

		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));
	}

	@Test
	public void addAndSearchQuadra() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);

		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));

	}

	@Test
	public void testRemove() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		a.remove(24);
//		assertEquals(true, a.search(3));
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.add(15);
		assertEquals(true, a.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
	}

	@Test
	public void testRemoveQuadra() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);

		b.remove(24);
		assertEquals(true, b.search(3));
		assertEquals("[0] (2) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());

		b.add(15);
		assertEquals(true, b.search(3));
		assertEquals("[0] (1) = 15 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
	}

	@Test
	public void prime() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		assertFalse(b.isPrime(0));
		assertFalse(b.isPrime(1));
		assertFalse(b.isPrime(4));
		assertFalse(b.isPrime(6));
		assertFalse(b.isPrime(9));
		assertFalse(b.isPrime(77));
		assertFalse(b.isPrime(98));
		assertFalse(b.isPrime(51));

		assertTrue(b.isPrime(2));
		assertTrue(b.isPrime(3));
		assertTrue(b.isPrime(37));
		assertTrue(b.isPrime(11));
		assertTrue(b.isPrime(29));
		assertTrue(b.isPrime(59));
		assertTrue(b.isPrime(97));
	}

	@Test
	public void nextPrime() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		assertEquals(3, b.getNextPrimeNumber(2));
		assertEquals(5, b.getNextPrimeNumber(3));
		assertEquals(5, b.getNextPrimeNumber(4));
		assertEquals(59, b.getNextPrimeNumber(55));
		assertEquals(97, b.getNextPrimeNumber(92));
	}

	@Test
	public void previousPrime() {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		assertEquals(2, b.getPrevPrimeNumber(3));
		assertEquals(5, b.getPrevPrimeNumber(6));
		assertEquals(11, b.getPrevPrimeNumber(12));
		assertEquals(97, b.getPrevPrimeNumber(100));
		assertEquals(83, b.getPrevPrimeNumber(87));
	}

	@Test
	public void testInteger() {
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(2, c.f(7, 0));
		assertEquals(4, c.f(7, 1));
		assertEquals(1, c.f(7, 2));
		assertEquals(3, c.f(7, 3));
		assertEquals(0, c.f(7, 4));

		assertEquals(0, c.f(0, 0));
		assertEquals(1, c.f(2, 4));
		assertEquals(2, c.f(3, 3));
		assertEquals(3, c.f(32, 1));
		assertEquals(4, c.f(1045, 2));
	}

	@Test
	public void testChar() {
		HashTable<Character> c = new HashTable<Character>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(0, c.f('A', 0));
		assertEquals(1, c.f('A', 1));
		assertEquals(2, c.f('A', 2));
		assertEquals(3, c.f('A', 3));
		assertEquals(4, c.f('A', 4));

		assertEquals(2, c.f('a', 0));
		assertEquals(4, c.f('a', 1));
		assertEquals(1, c.f('a', 2));
		assertEquals(3, c.f('a', 3));
		assertEquals(0, c.f('a', 4));
	}

	@Test
	public void testAddSearchRemove() {
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 1.0);
		c.add(4);
		c.add(13);
		c.add(24);
		c.add(3);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		c.remove(24);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		assertEquals(true, c.search(3));
		c.add(15);
		assertEquals(true, c.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
	}

	@Test
	public void testResizing() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		assertEquals(0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals(0.4, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.add(24); // DYNAMIC RESIZING!
		assertEquals(0.27, a.getLF(), 0.1);
		assertEquals(
				"[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ",
				a.toString());

	}
}
