package avl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import avl.AVLTree;

public class TreeTests {

	@Test
	public void testToString() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals("b(0)--", a.toString());
		a.add('a');
		assertEquals("b(-1)a(0)---", a.toString());
		a.add('d');
		assertEquals("b(0)a(0)--d(0)--", a.toString());
		a.add('c');
		assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());
		a.add('g');
		assertEquals("b(1)a(0)--d(0)c(0)--g(0)--", a.toString());
//		a.add('i');   												//No longer working due to rotations
//		assertEquals("ba--dc--g-i--", a.toString());
//		a.add('h');
//		assertEquals("ba--dc--g-ih---", a.toString());
	}

	@Test
	public void testSearch() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals(true, a.search('i'));
		assertEquals(false, a.search('f'));
	}

	@Test
	public void testGetMax() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals('i', (char) a.getMax(a.getRoot()));
	}

	@Test
	public void testRemove() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
		a.remove('b');
		assertEquals("d(0)a(1)-c(0)--h(0)g(0)--i(0)--", a.toString());
		a.remove('g');
		assertEquals("d(0)a(1)-c(0)--h(1)-i(0)--", a.toString());
	}

	@Test
	public void testBF() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
//		assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", a.toString()); No longer working due to rotations
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
	}

	@Test
	public void testUpdateBF() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
//		assertEquals("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", a.toString()); No longer working due to rotations
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
	}

}
