package group144.kidyankin;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] array) {
        heapify(array);

        for (int currentLength = array.length; currentLength > 0; currentLength--) {
            array[currentLength - 1] = delete(array, currentLength);
        }
    }

    private void heapify(T[] array) {
        for (int currentLength = 1; currentLength <= array.length; currentLength++) {
            int current = currentLength - 1;
            int parent = current / 2;
            while ((current > 0) && (array[current].compareTo(array[parent]) > 0)) {
                swap(array, current, parent);
                current = parent;
                parent = current / 2;
            }
        }
    }

    private T delete(T[] array, int length) {
        T result = array[0];
        int current = 0;
        int leftChild = current * 2 + 1;
        int rightChild = current * 2 + 2;
        array[0] = array[length - 1];
        length--;
        while ((rightChild < length) &&
                ((array[current].compareTo(array[leftChild]) < 0) || (array[current].compareTo(array[rightChild]) < 0))) {
            if (array[current].compareTo(array[leftChild]) < 0) {
                swap(array, current, leftChild);
                current = rightChild;
            } else {
                swap(array, current, rightChild);
                current = leftChild;
            }
            leftChild = current * 2 + 1;
            rightChild = current * 2 + 2;
        }
        if ((rightChild < length) && (array[current].compareTo(array[leftChild]) < 0)) {
            swap(array, current, leftChild);
        }
        return result;
    }

    private void swap(T[] array, int firstIndex, int secondIndex) {
        T tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }
}
