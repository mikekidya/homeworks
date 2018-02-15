package group144.kidyankin;

public class Stack {

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
    }

    private Node head;
    private int size;

    Stack() {
        head = null;
        size = 0;
    }

    public void add(int value) {
        head = new Node(value, head);
        size += 1;
    }

    public int pop() {
        int result = head.getValue();
        head = head.getNext();
        size -= 1;
        return result;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}


