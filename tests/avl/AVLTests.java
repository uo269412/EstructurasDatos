package avl;

import static org.junit.Assert.*;

import org.junit.Test;

import avl.AVLTree;

public class AVLTests {

	@Test
	public void testHeight() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		
		a.add('a');
		a.add('d');
		
//		System.out.println(a.getAvgLength());
//		System.out.println(a.toString());
//		System.out.println(a.countChildNodes(a.getRoot()));
//		System.out.println("Parent left child: " + a.getFather(a.getRoot().getRight()));
//		System.out.println("Parent left child: " + a.getFather(a.getRoot().getLeft()));
		a.add('c');
		System.out.println(a.toString());
		System.out.println(a.getAvgLength());
		a.add('g');
		a.add('i');
		a.add('h');
//		assertEquals("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString()); now it doesn't work due to rotations
		assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
//		System.out.println(a.countChildNodes(a.getRoot()));
//		System.out.println("Parent left child: " + a.getFather(a.getRoot().getLeft()));
//		System.out.println("Parent right child: " + a.getFather(a.getRoot().getRight().getRight()));
//		System.out.println(a.getAvgLength());

	}

//	@Test
//	public void testJoins() {
//	AVLTree<Character> a = new AVLTree<Character>();
//	a.add('b');
//	a.add('a');
//	a.add('d');
//	System.out.println(a.toString());
//	System.out.println(a.getRoot().getLeft());
//	System.out.println(a.getRoot().getRight());
//	System.out.println("Parent left child: " + a.getFather(a.getRoot().getLeft()));
//	System.out.println("Parent right child: " + a.getFather(a.getRoot().getRight()));
//	AVLTree<Character> b = new AVLTree<Character>();
//	b.add('c');
//	b.add('g');
//	b.add('i');
//	b.add('d');
////	assertEquals ("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.joins(b).toString()); now it doesn't work due to rotations
//	assertEquals ("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.joins(b).toString());
//	}

	@Test
	public void testIntersection() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		assertEquals ("d(0)--", a.intersection(b).toString());

	}
}
