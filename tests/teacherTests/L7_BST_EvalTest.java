package teacherTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import bst.AVLTree;

public class L7_BST_EvalTest
{
    @Test
    public void T1_add()
    {
        AVLTree<Character> tree = new AVLTree<Character>();

        tree.add('d');
        tree.add('f');
        tree.add('b');

        assertEquals("db--f--", tree.toString());

        tree.add('h');
        tree.add('g');
        tree.add('a');

        assertEquals("dba---f-hg---", tree.toString());

        tree.add('k');
        tree.add('c');
        tree.add('i');
        tree.add('e');
        tree.add('j');

        assertEquals("dba--c--fe--hg--ki-j---", tree.toString());
    }

    @Test
    public void T2_search()
    {
        AVLTree<Character> tree = new AVLTree<Character>();

        tree.add('b');
        tree.add('a');
        tree.add('d');
        tree.add('c');
        tree.add('g');
        tree.add('i');
        tree.add('h');

        assertEquals("ba--dc--g-ih---", tree.toString());

        assertTrue(tree.search('a'));
        assertTrue(tree.search('d'));
        assertTrue(tree.search('i'));
        assertTrue(tree.search('b'));
        assertFalse(tree.search('e'));
    }

    @Test
    public void T3_exceptions()
    {
        AVLTree<Character> tree = new AVLTree<Character>();

        assertEquals("-", tree.toString());

        assertFalse(tree.search('a'));

        tree.add('b');
        tree.add('a');
        tree.add('d');
        tree.add('c');
        tree.add('g');
        tree.add('i');
        tree.add('h');

        assertEquals("ba--dc--g-ih---", tree.toString());

        try
        {
            tree.add('d');
            fail("FAIL: unexpected exception raised in 'add'");
        }
        catch (Exception e) { }

        assertEquals("ba--dc--g-ih---", tree.toString());
    }
}