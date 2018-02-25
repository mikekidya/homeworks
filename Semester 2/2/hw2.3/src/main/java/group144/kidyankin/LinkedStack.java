package group144.kidyankin;

public class LinkedStack<ElementType> implements Stack<ElementType> {

    private class Node {
        private ElementType value;
        private Node next;

        Node(ElementType value, Node next) {
            this.value = value;
            this.next = next;
        }

        ElementType getValue() {
            return value;
        }

        Node getNext() {
            return next;
        }
    }

    private Node head = null;
    private int size = 0;

    @Override
    public void push(ElementType value) {
        head = new Node(value, head);
        size += 1;
    }

    @Override
    public ElementType pop() {
        ElementType result = head.getValue();
        head = head.getNext();
        size -= 1;
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
