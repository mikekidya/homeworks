package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

/** Test class for PriorityQueue */
public class PriorityQueueTest {

    @Test
    public void enqueueTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.enqueue("Peter", 6);
        priorityQueue.enqueue("Piper", 4);
        assertEquals(2, priorityQueue.getSize());
        assertEquals("Peter", priorityQueue.dequeue());
        priorityQueue.enqueue("Pepper", 10);
        assertEquals(2, priorityQueue.getSize());
        assertEquals("Pepper", priorityQueue.dequeue());
    }

    @Test
    public void dequeueTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.enqueue("Peter", 1);
        priorityQueue.enqueue("Piper", 2);
        assertEquals("Piper", priorityQueue.dequeue());
        assertEquals("Peter", priorityQueue.dequeue());
        priorityQueue.enqueue("Pepper", 4);
        priorityQueue.enqueue("Pickled", 2);
        assertEquals("Pepper", priorityQueue.dequeue());
    }

    /** Test for throwing exception from new empty queue */
    @Test (expected = EmptyPriorityQueueException.class)
    public void dequeueAlreadyEmptyExceptionTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.dequeue();
    }

    /** Test for throwing exception from queue that got empty after several pops */
    @Test (expected = EmptyPriorityQueueException.class)
    public void dequeueGotEmptyExceptionTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.enqueue("Peter", 4);
        priorityQueue.enqueue("Piper", 7);
        priorityQueue.dequeue();
        priorityQueue.dequeue();
        priorityQueue.dequeue();
    }

    @Test
    public void isEmptyTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.enqueue("Peter", 1);
        assertFalse(priorityQueue.isEmpty());
        priorityQueue.enqueue("Piper", 2);
        assertFalse(priorityQueue.isEmpty());
        priorityQueue.dequeue();
        assertFalse(priorityQueue.isEmpty());
        priorityQueue.dequeue();
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void getSizeTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        assertEquals(0, priorityQueue.getSize());
        priorityQueue.enqueue("Peter", 1);
        assertEquals(1, priorityQueue.getSize());
        priorityQueue.enqueue("Piper", 4);
        assertEquals(2, priorityQueue.getSize());
        priorityQueue.dequeue();
        assertEquals(1, priorityQueue.getSize());
        priorityQueue.dequeue();
        assertEquals(0, priorityQueue.getSize());
    }

    /** Test for queue that has no more than one element during its work */
    @Test
    public void onlyOneElementTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.enqueue("Peter", 1);
        assertEquals("Peter", priorityQueue.dequeue());
        priorityQueue.enqueue("Piper", 0);
        assertEquals("Piper", priorityQueue.dequeue());
        priorityQueue.enqueue("Pepper", 8);
        assertEquals("Pepper", priorityQueue.dequeue());
    }

    /** Test for queue that has many elements during its work */
    @Test
    public void manyElementsTest() throws EmptyPriorityQueueException {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.enqueue("Peter", 1);
        priorityQueue.enqueue("Piper", 8);
        priorityQueue.enqueue("Picked", 5);
        priorityQueue.enqueue("Peck", 6);
        priorityQueue.enqueue("Pickled", 10);
        priorityQueue.enqueue("Peppers", -1);
        assertEquals("Pickled", priorityQueue.dequeue());
        assertEquals("Piper", priorityQueue.dequeue());
        assertEquals("Peck", priorityQueue.dequeue());
        assertEquals("Picked", priorityQueue.dequeue());
        assertEquals("Peter", priorityQueue.dequeue());
        assertEquals("Peppers", priorityQueue.dequeue());
    }
}