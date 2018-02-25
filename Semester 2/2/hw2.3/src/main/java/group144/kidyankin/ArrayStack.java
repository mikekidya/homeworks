package group144.kidyankin;


public class ArrayStack<ElementType> implements Stack<ElementType> {

    private int maxSize = 10;
    private int currentPosition = 0;
    @SuppressWarnings("unchecked")
    private ElementType[] array = (ElementType[]) new Object[maxSize];


    @Override
    public void push(ElementType value) {
        if (currentPosition == maxSize) {
            enlarge();
        }
        array[currentPosition] = value;
        currentPosition++;
    }

    @Override
    public ElementType pop() {
        currentPosition--;
        return array[currentPosition];
    }

    @Override
    public boolean isEmpty() {
        return currentPosition == 0;
    }

    @Override
    public int getSize() {
        return currentPosition;
    }

    private void enlarge() {
        @SuppressWarnings("unchecked")
        ElementType[] newArray = (ElementType[]) new Object[maxSize * 2];
        System.arraycopy(array, 0, newArray, 0, maxSize);
        maxSize *= 2;
        array = newArray;
    }
}
