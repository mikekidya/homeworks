package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void isEmptyTest() {
        List<Integer> list = new List<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
        list.pop();
        assertTrue(list.isEmpty());
    }

    @Test
    public void getLengthTest() {
        List<Integer> list = new List<>();
        list.add(1);
        assertEquals(1, list.getLength());
        list.pop();
        assertEquals(0, list.getLength());
        list.add(1);
        list.add(2);
        assertEquals(2, list.getLength());
    }

    @Test
    public void addToHeadTest() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2), list.pop(1));
        assertEquals(Integer.valueOf(3), list.pop());
        assertEquals(Integer.valueOf(1), list.pop());
    }

    @Test
    public void addWithIndexTest() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2, 0);
        list.add(3, 0);
        list.add(4, 2);
        assertEquals(Integer.valueOf(1), list.pop());
        assertEquals(Integer.valueOf(3), list.pop());
        assertEquals(Integer.valueOf(2), list.pop());
        assertEquals(Integer.valueOf(4), list.pop());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWithIndexExceptionTest() {
        List<String> list = new List<>();
        list.add("Hello", 3);
    }

    @Test
    public void popFromHeadTest() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(3), list.pop());
        assertEquals(Integer.valueOf(2), list.pop());
        assertEquals(Integer.valueOf(1), list.pop());
    }

    @Test(expected = NoSuchElementException.class)
    public void popFromHeadExceptionTest() {
        List<String> list = new List<>();
        list.pop();
        list.add("Hello");
        list.pop();
        list.pop();
    }

    @Test
    public void popWithIndexTest() {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2), list.pop(1));
        assertEquals(Integer.valueOf(3), list.pop(0));
        assertEquals(Integer.valueOf(1), list.pop());
    }

    @Test(expected = NoSuchElementException.class)
    public void popWithIndexExceptionTest() {
        List<String> list = new List<>();
        list.pop(3);
        list.add("Hello");
        list.pop(0);
        list.pop(0);
    }

    @Test
    public void find() {
        List<String> list = new List<>();
        list.add("peter");
        list.add("piper");
        assertEquals(0, list.find("piper"));
        assertEquals(1, list.find("peter"));
        assertEquals(-1, list.find("pepper"));
    }
}