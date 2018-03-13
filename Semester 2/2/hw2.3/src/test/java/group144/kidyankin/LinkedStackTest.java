package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedStackTest {

    @Test
    public void popTest() {
        Stack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(stack.pop().equals(3));
        assertTrue(stack.pop().equals(2));
        assertTrue(stack.pop().equals(1));
    }

    @Test(expected = EmptyStackException.class)
    public void popExceptionTest() {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        assertTrue(stack.pop().equals(1));
        stack.pop();
    }

    @Test
    public void getSizeTest() {
        Stack<Integer> stack = new LinkedStack<>();
        assertEquals(0, stack.getSize());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.getSize());
        stack.pop();
        assertEquals(1, stack.getSize());
    }

    @Test
    public void isEmptyTest() {
        Stack<Integer> stack = new LinkedStack<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}