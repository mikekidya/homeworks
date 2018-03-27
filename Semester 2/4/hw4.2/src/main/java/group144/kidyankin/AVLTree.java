package group144.kidyankin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class realizing Collection as AVL tree.
 *
 * @param <T> Type of elements. Should be comparable
 */
public class AVLTree<T extends Comparable<T>> implements Collection<T> {

    private Node root = null;
    private int size = 0;

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if there are no elements in this collection.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if collection contains the specified element.
     *
     * @param o element whose presence in this collection is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return !isEmpty() && root.contains((T) o);
    }

    /**
     * Returns an iterator over the elements in this collection in increasing order.
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return new AVLTreeIterator();
    }

    /**
     * Returns an array containing all of the elements in this collection in increasing order.
     *
     * @return an array containing all of the elements in this collection
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]) ;
    }

    /**
     * Returns an array containing all of the elements in this collection in increasing order;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     *
     * @param <T1> the runtime type of the array to contain the collection
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        ArrayList<T1> arrayList = new ArrayList<>();
        for (T tmp : this) {
            arrayList.add((T1) tmp);
        }
        return arrayList.toArray(a);
    }

    /**
     * Adds a new element into this collection.
     *
     * @param value the value should be added
     * @return <tt>true</tt> if collection has changed
     */
    @Override
    public boolean add(T value) {
        if (isEmpty()) {
            root = new Node(value, null);
        } else {
            root.addNode(value);
        }
        size++;
        return true;
    }


    /**
     * Removes a single instance of the specified element from this collection, if it is present
     *
     * @param o an element should be removed
     * @return <tt>true</tt> if collection has changed
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        return !isEmpty() && root.removeNode((T) o);
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements in the specified collection.
     *
     * @param  c collection to be checked for containment in this collection
     * @return <tt>true</tt> if this collection contains all of the elements in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object tmp : c) {
            result = result && contains(tmp);
        }
        return result;
    }

    /**
     * Adds all of the elements in the specified collection to this collection.
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T tmp : c) {
            add(tmp);
        }
        return !c.isEmpty();
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object tmp : c) {
            result = remove(tmp) || result;
        }
        return result;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (T tmp : this) {
            if (!c.contains(tmp)) {
                remove(tmp);
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        return isEmpty() ? "null" : root.toString();
    }


    /**
     * Class realizing the AVL Tree Iterator
     */
    private class AVLTreeIterator implements Iterator<T> {
        Node current;
        int repeatCounter;

        AVLTreeIterator() {
            if (isEmpty()) {
                current = null;
                return;
            }
            current = root;
            while (current.leftChild != null) {
                current = current.leftChild;
            }
            repeatCounter = current.number;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (repeatCounter > 1) {
                repeatCounter--;
                return current.value;
            }

            T result = current.value;

            if (current.rightChild == null) {
                if (current.parent == null) {
                    current = null;
                } else if (current.equals(current.parent.leftChild)) {
                    current = current.parent;
                } else {
                    while (current.parent != null && current.equals(current.parent.rightChild)) {
                        current = current.parent;
                    }
                    if (current.parent == null) {
                        current = null;
                    } else {
                        current = current.parent;
                    }
                }
            } else {
                current = current.rightChild;
                while (current.leftChild != null) {
                    current = current.leftChild;
                }
            }

            if (current != null) {
                repeatCounter = current.number;
            }
            return result;
        }
    }


    /**
     * Class realizing AVL Tree Nodes – elements AVL Tree technically contains.
     * Realizing some operations like add, remove and rotations.
     */
    private class Node {
        T value;
        Node parent;
        Node leftChild = null;
        Node rightChild = null;
        int height = 1;
        int number = 1;

        Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        public void addNode(T value) {
            if (value.equals(this.value)) {
                number++;
                return;
            }

            if (value.compareTo(this.value) < 0) {
                if (leftChild == null) {
                    leftChild = new Node(value, this);
                } else {
                    leftChild.addNode(value);
                }
            } else {
                if (rightChild == null) {
                    rightChild = new Node(value, this);
                } else {
                    rightChild.addNode(value);
                }
            }
            balance();
        }

        public boolean removeNode(T value) {
            boolean result;
            if (value.compareTo(this.value) < 0) {
                result = (leftChild != null) && leftChild.removeNode(value);
            } else if (value.compareTo(this.value) > 0) {
                result = (rightChild != null) && rightChild.removeNode(value);
            } else {
                size--;
                if (number > 1) {
                    number--;
                } else {
                    if (leftChild != null && rightChild != null) {
                        Node newNode = leftChild;
                        while (newNode.rightChild != null) {
                            newNode = newNode.rightChild;
                        }
                        changeNode(newNode);
                    } else if (leftChild != null) {
                        changeNode(leftChild);
                    } else if (rightChild != null) {
                        changeNode(rightChild);
                    } else {
                        changeNode(null);
                    }
                }
                result = true;
            }
            balance();
            return result;
        }

        public boolean contains(T value) {
            if (value.equals(this.value)) {
                return true;
            }

            if (value.compareTo(this.value) < 0) {
                return (leftChild != null) && leftChild.contains(value);
            } else {
                return (rightChild != null) && rightChild.contains(value);
            }
        }

        private void changeNode(Node newNode) {
            if (newNode == null) {
                if (parent == null) {
                    root = null;
                } else {
                    if (equals(parent.leftChild)) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
                return;
            }

            value = newNode.value;
            number = newNode.number;

            if (newNode.equals(newNode.parent.leftChild)) {
                newNode.parent.leftChild = null;
            } else {
                newNode.parent.rightChild = null;
            }

        }

        private void updateHeight() {
            height = Integer.max(leftChild != null ? leftChild.height : 0, rightChild != null ? rightChild.height : 0) + 1;
        }

        private void balance() {
            updateHeight();

            if (balanceFactor() == 2) {
                if (leftChild.balanceFactor() < 0) {
                    leftChild.leftRotate();
                }
                rightRotate();
            } else if (balanceFactor() == -2) {
                if (rightChild.balanceFactor() > 0) {
                    rightChild.rightRotate();
                }
                leftRotate();
            }
        }

        private int balanceFactor() {
            return (leftChild != null ? leftChild.height : 0) - (rightChild != null ? rightChild.height : 0);
        }

        private void rightRotate() {
            Node pivot = leftChild;
            if (pivot.rightChild != null)
                pivot.rightChild.parent = this;
            leftChild = pivot.rightChild;
            pivot.rightChild = this;

            if (parent == null) {
                root = pivot;
            } else {
                if (equals(parent.leftChild)) {
                    parent.leftChild = pivot;
                } else {
                    parent.rightChild = pivot;
                }
            }
            pivot.parent = parent;
            parent = pivot;

            this.updateHeight();
            pivot.updateHeight();
        }

        private void leftRotate() {
            Node pivot = rightChild;
            if (pivot.leftChild != null)
                pivot.leftChild.parent = this;
            rightChild = pivot.leftChild;
            pivot.leftChild = this;

            if (parent == null) {
                root = pivot;
            } else {
                if (equals(parent.rightChild)) {
                    parent.rightChild = pivot;
                } else {
                    parent.leftChild = pivot;
                }
            }
            pivot.parent = parent;
            parent = pivot;

            this.updateHeight();
            pivot.updateHeight();
        }

        @Override
        public String toString() {
            String result = "(" + value.toString() + ":" + number + " ";
            result += leftChild != null ? leftChild.toString() : "null";
            result += " ";
            result += rightChild != null ? rightChild.toString() : "null";
            result += ")";
            return result;
        }
    }
}
