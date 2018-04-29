package group144.kidyankin;

/**
 * Class realizing sort based on one-thread quick sort algorithm.
 *
 * @param <T> Comparable type of array elements
 */
public class OrdinaryQsorter<T extends Comparable<T>> implements Qsorter<T> {

    /**
     * Sort the array with order given by type default comparator.
     *
     * @param array array will be sorted
     */
    @Override
    public void sort(T[] array) {
        recursiveQSort(array, 0, array.length - 1);
    }

    /** Main method realizing QSort algorithm */
    private void recursiveQSort(T[] array, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }

        T pivot = array[(startIndex + endIndex) / 2];
        int i = startIndex;
        int j = endIndex;
        while (i <= j) {
            while (array[i].compareTo(pivot) < 0) {
                i++;
            }

            while (array[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        recursiveQSort(array, startIndex, i - 1);
        recursiveQSort(array, i, endIndex);
    }
}
