package group144.kidyankin;

public class List {
    private class Node {
        private int value;
        private Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    private Node head;
    private int length;

    List() {
        head = null;
        length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int getLength() {
        return length;
    }

    // add new element in the head
    public void add(int value) {
        head = new Node(value, head);
        length++;
    }

    // add new element after certain index
    public void add(int value, int index) {
        Node tmp = head;
        for (int i = 0; i < index; i++)
            tmp = tmp.getNext();
        Node newElement = new Node(value, tmp.getNext());
        tmp.setNext(newElement);
        length++;
    }

    // pop element from the head
    public int pop() {
        if (isEmpty())
            return -1;
        int result = head.getValue();
        head = head.getNext();
        length--;
        return result;
    }

    // pop element with certain index
    public int pop(int index) {
        if (index == 0)
            return pop();
        if (index >= getLength())
            return -1;
        Node tmp = head;
        for (int i = 0; i < index - 1; i++)
            tmp = tmp.getNext();
        int result = tmp.getNext().getValue();
        tmp.setNext(tmp.getNext().getNext());
        length--;
        return result;
    }

    // returns the index of element
    public int find(int value) {
        Node tmp = head;
        for (int i = 0; i < getLength(); i++) {
            if (tmp.getValue() == value)
                return i;
            tmp = tmp.getNext();
        }
        return -1;
    }
}
