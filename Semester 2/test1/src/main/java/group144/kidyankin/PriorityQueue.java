package group144.kidyankin;

/**
 * Class realizing a queue with elements are in order of their priority
 *
 * @param <T> Type of contained elements
 */
public class PriorityQueue<T> {

    /** Class realizing a queue element
     *
     * @param <T> Type of value element
     */
    private class QueueNode<T> {
        private T value;
        private int priority;
        private QueueNode<T> next;

        /**
         * Constructor for QueueNode elements
         *
         * @param value the value it has
         * @param priority the priority it has
         * @param next the next QueueNode element
         */
        QueueNode(T value, int priority, QueueNode<T> next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }

        /** Returns a priority of this node */
        public int getPriority() {
            return priority;
        }

        /** Returns a value of this node */
        public T getValue() {
            return value;
        }

        /** Returns the next element for this node */
        public QueueNode<T> getNext() {
            return next;
        }

        /**
         * Set the next Queue node for this node
         *
         * @param next the next QueueNode element
         */
        public void setNext(QueueNode<T> next) {
            this.next = next;
        }
    }


    private int size = 0;
    private QueueNode<T> head = null;


    /**
     * Puts a new value into queue with given priority
     *
     * @param value the value should be put
     * @param priority the priority of value should be put
     */
    public void enqueue(T value, int priority) {
        QueueNode<T> currentNode = head;
        size++;

        if (isEmpty() || head.getPriority() < priority) {
            head = new QueueNode<>(value, priority, head);
            return;
        }

        while (currentNode.getNext() != null && currentNode.getNext().getPriority() > priority) {
            currentNode = currentNode.getNext();
        }
        QueueNode<T> newNode = new QueueNode<>(value, priority, currentNode.getNext());
        currentNode.setNext(newNode);
    }


    /**
     * Returns the value from queue with highest priority
     *
     * @return the value with highest priority
     * @throws EmptyPriorityQueueException if nothing to return (queue is empty)
     */
    public T dequeue() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException();
        }
        T result = head.getValue();
        head = head.getNext();
        size--;
        return result;
    }


    /** Returns if queue is empty */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of values in queue
     *
     * @return the number of elements
     */
    public int getSize() {
        return size;
    }
}
