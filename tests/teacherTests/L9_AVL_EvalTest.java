package teacherTests;
import static org.junit.Assert.*;

import org.junit.Test;

import avl.AVLTree;

public class L9_AVL_EvalTest
{
    @Test
    public void T1_add()
    {
        AVLTree<Character> a = new AVLTree<Character>();

        assertEquals(0, a.getHeight());

        a.add('b');

        assertEquals("b(0)--", a.toString());
        assertEquals(1, a.getHeight());

        a.add('a');

        assertEquals("b(-1)a(0)---", a.toString());
        assertEquals(2, a.getHeight());

        a.add('d');

        assertEquals("b(0)a(0)--d(0)--", a.toString());
        assertEquals(2, a.getHeight());

        a.add('c');

        assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());
        assertEquals(3, a.getHeight());

        a.add('g');

        assertEquals("b(1)a(0)--d(0)c(0)--g(0)--", a.toString());
        assertEquals(3, a.getHeight());

        a.add('i');

        assertEquals("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.toString());
        assertEquals(3, a.getHeight());

        a.add('h');

        assertEquals("d(0)b(0)a(0)--c(0)--h(0)g(0)--i(0)--", a.toString());
        assertEquals(3, a.getHeight());

        a.add('e');

        assertEquals("d(1)b(0)a(0)--c(0)--h(-1)g(-1)e(0)---i(0)--", a.toString());
        assertEquals(4, a.getHeight());

        a.add('f');

        assertEquals("d(1)b(0)a(0)--c(0)--h(-1)f(0)e(0)--g(0)--i(0)--", a.toString());
        assertEquals(4, a.getHeight());

        a.add('l');

        assertEquals("d(1)b(0)a(0)--c(0)--h(0)f(0)e(0)--g(0)--i(1)-l(0)--", a.toString());
        assertEquals(4, a.getHeight());

        a.add('j');

        assertEquals("d(1)b(0)a(0)--c(0)--h(0)f(0)e(0)--g(0)--j(0)i(0)--l(0)--", a.toString());
        assertEquals(4, a.getHeight());

        a.add('k');

        assertEquals("h(0)d(0)b(0)a(0)--c(0)--f(0)e(0)--g(0)--j(1)i(0)--l(-1)k(0)---", a.toString());
        assertEquals(4, a.getHeight());
    }

    @Test
    public void T2_full()
    {
        AVLTree<Integer> t = new AVLTree<Integer>();

        t.add(10);
        t.add(6);
        t.add(15);
        t.add(3);
        t.add(9);
        t.add(14);
        t.add(20);

        assertEquals("10(0)6(0)3(0)--9(0)--15(0)14(0)--20(0)--", t.toString());
        assertEquals(3, t.getHeight());

        t.add(2);
        t.add(4);
        t.add(7);
        t.add(12);

        assertEquals("10(0)6(0)3(0)2(0)--4(0)--9(-1)7(0)---15(-1)14(-1)12(0)---20(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.add(1);

        assertEquals("10(-1)6(-1)3(-1)2(-1)1(0)---4(0)--9(-1)7(0)---15(-1)14(-1)12(0)---20(0)--", t.toString());
        assertEquals(5, t.getHeight());

        t.remove(20);

        assertEquals("6(0)3(-1)2(-1)1(0)---4(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove(4);

        assertEquals("6(1)2(0)1(0)--3(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove(10);

        assertEquals("6(1)2(0)1(0)--3(0)--9(1)7(0)--14(0)12(0)--15(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove(9);

        assertEquals("6(1)2(0)1(0)--3(0)--14(-1)7(1)-12(0)--15(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove(6);

        assertEquals("3(1)2(-1)1(0)---14(-1)7(1)-12(0)--15(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove(3);

        assertEquals("7(0)2(-1)1(0)---14(0)12(0)--15(0)--", t.toString());
        assertEquals(3, t.getHeight());

        t.remove(2);

        assertEquals("7(1)1(0)--14(0)12(0)--15(0)--", t.toString());
        assertEquals(3, t.getHeight());

        t.remove(14);

        assertEquals("7(1)1(0)--12(1)-15(0)--", t.toString());
        assertEquals(3, t.getHeight());

        t.remove(12);

        assertEquals("7(0)1(0)--15(0)--", t.toString());
        assertEquals(2, t.getHeight());

        t.remove(7);

        assertEquals("1(1)-15(0)--", t.toString());
        assertEquals(2, t.getHeight());

        t.remove(1);

        assertEquals("15(0)--", t.toString());
        assertEquals(1, t.getHeight());

        t.remove(15);

        assertEquals("-", t.toString());
        assertEquals(0, t.getHeight());
    }

    @Test
    public void T3_full()
    {
        AVLTree<Character> t = new AVLTree<Character>();

        t.add('m');
        t.add('h');
        t.add('p');
        t.add('t');
        t.add('v');
        t.add('w');
        t.add('b');
        t.add('a');
        t.add('z');

        assertEquals("t(-1)m(-1)b(0)a(0)--h(0)--p(0)--w(0)v(0)--z(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.add('g');
        t.add('i');
        t.add('y');
        t.add('r');
        t.add('q');
        t.add('n');

        assertEquals("t(-1)h(1)b(0)a(0)--g(0)--p(0)m(0)i(0)--n(0)--q(1)-r(0)--w(1)v(0)--z(-1)y(0)---", t.toString());
        assertEquals(5, t.getHeight());

        t.remove('b');

        assertEquals("t(-1)h(1)a(1)-g(0)--p(0)m(0)i(0)--n(0)--q(1)-r(0)--w(1)v(0)--z(-1)y(0)---", t.toString());
        assertEquals(5, t.getHeight());

        t.remove('w');
        t.remove('m');
        t.remove('q');

        assertEquals("p(0)h(0)a(1)-g(0)--i(1)-n(0)--t(1)r(0)--y(0)v(0)--z(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove('g');
        t.remove('p');
        t.remove('a');

        assertEquals("n(1)h(1)-i(0)--t(1)r(0)--y(0)v(0)--z(0)--", t.toString());
        assertEquals(4, t.getHeight());

        t.remove('h');
        t.remove('i');
        t.remove('n');

        assertEquals("t(1)r(0)--y(0)v(0)--z(0)--", t.toString());
        assertEquals(3, t.getHeight());

        t.remove('v');
        t.remove('z');
        t.remove('r');

        assertEquals("t(1)-y(0)--", t.toString());
        assertEquals(2, t.getHeight());
    }
}