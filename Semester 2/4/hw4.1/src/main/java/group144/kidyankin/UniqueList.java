package group144.kidyankin;

/**
 * Class realizing Simple Linked List without repetitive elements
 *
 * @param <T> Type parameter
 */
public class UniqueList<T> extends List<T> {

    /**
     * Adds element to list
     *
     * @param value added value
     * @throws ElementAlreadyAddedException if element is already added
     */
    @Override
    public void add(T value) throws ElementAlreadyAddedException {
        if (super.find(value) != -1) {
            throw new ElementAlreadyAddedException();
        }
        super.add(value);
    }

    /**
     * Add new element to list with certain index
     *
     * @param value added value
     * @param index index of element after which new one will be added
     * @throws IndexOutOfBoundsException if index out of range
     * @throws ElementAlreadyAddedException if element is already added
     */
    public void add(T value, int index) throws IndexOutOfBoundsException, ElementAlreadyAddedException{
        if (super.find(value) != -1) {
            throw new ElementAlreadyAddedException();
        }
        super.add(value, index);
    }

}