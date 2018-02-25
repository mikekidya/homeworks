package group144.kidyankin;

public interface Stack<ElementType> {

    void push(ElementType value);

    ElementType pop();

    boolean isEmpty();

    int getSize();
}
