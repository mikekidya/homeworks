package group244.kidyankin;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void sizeTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Piper");
        assertEquals(2, tree.size());
        tree.remove("Hello");
        assertEquals(2, tree.size());
        tree.remove("Peter");
        assertEquals(1, tree.size());
        tree.add("Piper");
        assertEquals(2, tree.size());
        tree.remove("Piper");
        tree.remove("Piper");
        assertEquals(0, tree.size());
    }

    @Test
    public void isEmptyTest() {
        Collection<String> tree = new BinarySearchTree<>();
        assertTrue(tree.isEmpty());
        tree.add("Peter");
        assertFalse(tree.isEmpty());
        tree.add("Peter");
        tree.remove("Peter");
        assertFalse(tree.isEmpty());
        tree.remove("Peter");
        assertTrue(tree.isEmpty());
    }

    @Test
    public void containsTest() {
        Collection<String> tree = new BinarySearchTree<>();
        assertFalse(tree.contains("Peter"));
        tree.add("Piper");
        assertTrue(tree.contains("Piper"));
        tree.remove("Piper");
        assertFalse(tree.contains("Piper"));
    }

    @Test
    public void toArrayTest() {
        Collection<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(3);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 5}, tree.toArray());
    }

    @Test
    public void toArray2Test() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("a");
        tree.add("d");
        tree.add("a");
        tree.add("b");
        tree.add("a");
        assertArrayEquals(new String[]{"a", "a", "a", "b", "d"}, tree.toArray(new String[5]));
    }

    @Test
    public void addTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Piper");
        assertEquals(2, tree.size());
        assertTrue(tree.contains("Peter"));
        assertFalse(tree.contains("Pepper"));
    }

    @Test
    public void removeTest() {
        Collection<String> tree = new BinarySearchTree<>();
        assertFalse(tree.remove("Peter"));
        tree.add("Peter");
        tree.add("Peter");
        tree.add("Piper");
        assertTrue(tree.remove("Peter"));
        assertTrue(tree.contains("Peter"));
        tree.remove("Peter");
        assertFalse(tree.contains("Peter"));
        assertFalse(tree.isEmpty());
    }

    @Test
    public void containsAllTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Piper");
        tree.add("Pepper");
        Collection<String> collection = new ArrayList<>();
        collection.add("Peter");
        collection.add("Piper");
        assertTrue(tree.containsAll(collection));
        collection.add("Pepper");
        assertTrue(tree.containsAll(collection));
        collection.add("Peck");
        assertFalse(tree.containsAll(collection));
    }

    @Test
    public void addAllTest() {
        Collection<Integer> tree = new BinarySearchTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        tree.addAll(collection);
        assertTrue(tree.containsAll(collection));
        assertEquals(collection.size(), tree.size());
    }

    @Test
    public void removeAllTest() {
        Collection<Integer> tree = new BinarySearchTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.removeAll(collection);
        assertEquals(1, tree.size());
        assertTrue(tree.contains(3));
    }

    @Test
    public void retainAllTest() {
        Collection<Integer> tree = new BinarySearchTree<>();
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.retainAll(collection);
        assertEquals(2, tree.size());
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
    }

    @Test
    public void clearTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Peter");
        tree.add("Piper");
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void toStringTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Peter");
        tree.add("Piper");
        assertEquals("(Peter:2 null (Piper:1 null null))", tree.toString());
    }

    @Test
    public void iteratorTest() {
        Collection<String> tree = new BinarySearchTree<>();
        tree.add("Peter");
        tree.add("Peter");
        tree.add("Piper");
        Iterator<String> avlTreeIterator = tree.iterator();
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "Peter");
        assertEquals(avlTreeIterator.next(), "Peter");
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "Piper");
        assertFalse(avlTreeIterator.hasNext());
    }

    @Test
    public void foreachTest() {
        Collection<String> tree = new BinarySearchTree<>();
        LinkedList<String> iterated = new LinkedList<>();
        tree.add("Peter");
        tree.add("Peter");
        tree.add("Piper");
        for (String element : tree) {
            iterated.add(element);
        }
        assertEquals(iterated.getFirst(), "Peter");
        iterated.removeFirst();
        assertEquals(iterated.getFirst(), "Peter");
        iterated.removeFirst();
        assertEquals(iterated.getFirst(), "Piper");
    }
}