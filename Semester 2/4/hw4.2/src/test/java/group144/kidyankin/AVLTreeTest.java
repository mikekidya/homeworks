package group144.kidyankin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    public void balanceTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        assertEquals("(2:1 (1:1 null null) (3:1 null null))", avlTree.toString());
        for (int i = 4; i < 16; i++) {
            avlTree.add(i);
        }
        String EXPECTED = "(8:1 (4:1 (2:1 (1:1 null null) (3:1 null null)) (6:1 (5:1 null null) (7:1 null null)))" +
                " (12:1 (10:1 (9:1 null null) (11:1 null null)) (14:1 (13:1 null null) (15:1 null null))))";
        assertEquals(EXPECTED, avlTree.toString());
        for (int i = 1; i <= 10; i++) {
            avlTree.remove(i);
        }
        assertEquals("(12:1 (11:1 null null) (14:1 (13:1 null null) (15:1 null null)))", avlTree.toString());
        avlTree.remove(11);
        avlTree.remove(12);
        assertEquals("(14:1 (13:1 null null) (15:1 null null))", avlTree.toString());
    }

    @Test
    public void sizeTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Piper");
        assertEquals(2, avlTree.size());
        avlTree.remove("Hello");
        assertEquals(2, avlTree.size());
        avlTree.remove("Peter");
        assertEquals(1, avlTree.size());
        avlTree.add("Piper");
        assertEquals(2, avlTree.size());
        avlTree.remove("Piper");
        avlTree.remove("Piper");
        assertEquals(0, avlTree.size());
    }

    @Test
    public void isEmptyTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        assertTrue(avlTree.isEmpty());
        avlTree.add("Peter");
        assertFalse(avlTree.isEmpty());
        avlTree.add("Peter");
        avlTree.remove("Peter");
        assertFalse(avlTree.isEmpty());
        avlTree.remove("Peter");
        assertTrue(avlTree.isEmpty());
    }

    @Test
    public void containsTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        assertFalse(avlTree.contains("Peter"));
        avlTree.add("Piper");
        assertTrue(avlTree.contains("Piper"));
        avlTree.remove("Piper");
        assertFalse(avlTree.contains("Piper"));
    }

    @Test
    public void toArrayTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(5);
        avlTree.add(3);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 5}, avlTree.toArray());
    }

    @Test
    public void toArray2Test() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("a");
        avlTree.add("d");
        avlTree.add("a");
        avlTree.add("b");
        avlTree.add("a");
        assertArrayEquals(new String[]{"a", "a", "a", "b", "d"}, avlTree.toArray(new String[5]));
    }

    @Test
    public void addTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Piper");
        assertEquals(2, avlTree.size());
        assertTrue(avlTree.contains("Peter"));
        assertFalse(avlTree.contains("Pepper"));
    }

    @Test
    public void removeTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        assertFalse(avlTree.remove("Peter"));
        avlTree.add("Peter");
        avlTree.add("Peter");
        avlTree.add("Piper");
        assertTrue(avlTree.remove("Peter"));
        assertTrue(avlTree.contains("Peter"));
        avlTree.remove("Peter");
        assertFalse(avlTree.contains("Peter"));
        assertFalse(avlTree.isEmpty());
    }

    @Test
    public void containsAllTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Piper");
        avlTree.add("Pepper");
        Collection<String> collection = new ArrayList<>();
        collection.add("Peter");
        collection.add("Piper");
        assertTrue(avlTree.containsAll(collection));
        collection.add("Pepper");
        assertTrue(avlTree.containsAll(collection));
        collection.add("Peck");
        assertFalse(avlTree.containsAll(collection));
    }

    @Test
    public void addAllTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        avlTree.addAll(collection);
        assertTrue(avlTree.containsAll(collection));
        assertEquals(collection.size(), avlTree.size());
    }

    @Test
    public void removeAllTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.removeAll(collection);
        assertEquals(1, avlTree.size());
        assertTrue(avlTree.contains(3));
    }

    @Test
    public void retainAllTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.retainAll(collection);
        assertEquals(2, avlTree.size());
        assertTrue(avlTree.contains(1));
        assertTrue(avlTree.contains(2));
    }

    @Test
    public void clearTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Peter");
        avlTree.add("Piper");
        assertFalse(avlTree.isEmpty());
        avlTree.clear();
        assertTrue(avlTree.isEmpty());
    }

    @Test
    public void toStringTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Peter");
        avlTree.add("Piper");
        assertEquals("(Peter:2 null (Piper:1 null null))", avlTree.toString());
    }

    @Test
    public void iteratorTest() {
        AVLTree<String> avlTree = new AVLTree<>();
        avlTree.add("Peter");
        avlTree.add("Peter");
        avlTree.add("Piper");
        Iterator<String> avlTreeIterator = avlTree.iterator();
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "Peter");
        assertEquals(avlTreeIterator.next(), "Peter");
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "Piper");
        assertFalse(avlTreeIterator.hasNext());
    }
}