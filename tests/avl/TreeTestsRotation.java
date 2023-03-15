package avl;

import static org.junit.Assert.*;

import org.junit.Test;

import avl.AVLTree;

public class TreeTestsRotation {
	
	
	@Test
	public void testSingleRotations() {
		
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('c');
		a.add('d');
		a.add('e');
		assertEquals("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());

		a.add('f');
		assertEquals("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
		
		
		AVLTree<Integer> b = new AVLTree<Integer>();
		b.add(5);
		b.add(2);
		b.add(7);
		b.add(1);
		b.add(4);
		b.add(6);
		b.add(3);
		b.add(8);
		b.add(10);
		b.add(11);
		
		assertEquals("5(0)2(1)1(0)--4(-1)3(0)---7(1)6(0)--10(0)8(0)--11(0)--", b.toString());
		
		AVLTree<Integer> c = new AVLTree<Integer>();
		c.add(7);
		c.add(5);
		c.add(10);
		c.add(3);
		c.add(6);
		c.add(8);
		c.add(11);
		c.add(2);
		c.add(4);
		c.add(9);
		c.add(1);
		
		assertEquals("7(0)3(0)2(-1)1(0)---5(0)4(0)--6(0)--10(-1)8(1)-9(0)--11(0)--", c.toString());
		
	}

	@Test
	public void testDoubleRotations() {
		
	AVLTree<Character> a = new AVLTree<Character>();
	a.add('e');
	a.add('g');
	a.add('b');
	a.add('d');
	a.add('c');
	assertEquals ("e(-1)c(0)b(0)--d(0)--g(0)--", a.toString());
	
	
	AVLTree<Integer> b = new AVLTree<Integer>();
	b.add(7);
	b.add(5);
	b.add(10);
	b.add(2);
	b.add(6);
	b.add(8);
	b.add(11);
	b.add(1);
	b.add(3);
	b.add(9);
	b.add(4);
	
	assertEquals("7(0)3(0)2(-1)1(0)---5(0)4(0)--6(0)--10(-1)8(1)-9(0)--11(0)--", b.toString());
	
	AVLTree<Integer> c = new AVLTree<Integer>();
	c.add(5);
	c.add(2);
	c.add(7);
	c.add(1);
	c.add(4);
	c.add(6);
	c.add(10);
	c.add(3);
	c.add(9);
	c.add(11);
	c.add(8);
	
	assertEquals("5(0)2(1)1(0)--4(-1)3(0)---9(0)7(0)6(0)--8(0)--10(1)-11(0)--", c.toString());
	}
	
	@Test
	public void fullTest() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('d');
		assertEquals ("b(0)a(0)--d(0)--", a.toString());

		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		assertEquals ("g(-1)c(1)-d(0)--i(0)--", b.toString());

		assertEquals ("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.joins(b).toString());
	}

	@Test
	public void testHeight() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals (3, a.getHeight());

	}

}
