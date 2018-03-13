package group144.kidyankin;

public interface Stack<ElementType> {

    void push(ElementType value);

    ElementType pop() throws EmptyStackException;

    boolean isEmpty();

    int getSize();
}
