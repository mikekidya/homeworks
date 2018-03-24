package group144.kidyankin;

/**
 * Class realizing Simple Linked List
 * @param <T> Type parameter
 */
public class List<T> {

    /**
     * Class realizing list element
     *
     * @param <T> type parameter
     */
    private class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    private Node<T> head = null;
    private int length = 0;

    /**
     * Returns true f list has no elements and false if it has
     *
     * @return is list empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the number of elements in list
     *
     * @return the number of elements
     */
    public int getLength() {
        return length;
    }

    /**
     * Add new element to the list's head
     *
     * @param value added value
     */
    public void add(T value) {
        head = new Node<>(value, head);
        length++;
    }

    /**
     * Add new element to list with certain index
     *
     * @param value added value
     * @param index index of element after which new one will be added
     * @throws IndexOutOfBoundsException if index out of range
     */
    public void add(T value, int index) throws IndexOutOfBoundsException {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        Node<T> newElement = new Node<>(value, tmp.getNext());
        tmp.setNext(newElement);
        length++;
    }

    /**
     * Pops element from the list's head
     *
     * @throws NoSuchElementException if list is empty
     * @return the value in list's head
     */
    public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = head.getValue();
        head = head.getNext();
        length--;
        return result;
    }

    /**
     * Pops element from the list with certain index
     *
     * @param index index of element should be popped
     * @throws NoSuchElementException if index out of range
     * @return the value of element should be popped
     */
    public T pop(int index) throws NoSuchElementException {
        if (index == 0) {
            return pop();
        }
        if (index >= getLength()) {
            throw new NoSuchElementException();
        }
        Node<T> tmp = head;
        for (int i = 0; i < index - 1; i++) {
            tmp = tmp.getNext();
        }
        T result = tmp.getNext().getValue();
        tmp.setNext(tmp.getNext().getNext());
        length--;
        return result;
    }

    /**
     * Returns the first index of element or -1 if is not found
     *
     * @param value the value should be found
     * @return the first index or -1
     */
    public int find(T value) {
        Node tmp = head;
        for (int i = 0; i < getLength(); i++) {
            if (tmp.getValue() == value) {
                return i;
            }
            tmp = tmp.getNext();
        }
        return -1;
    }
}
