package group144.kidyankin;

/**
 * Interface realizing quick sort algorithm
 *
 * @param <T> Comparable type of array elements
 */
public interface Qsorter<T extends Comparable<T>> {

    /**
     * Sort the array with order given by type default comparator.
     *
     * @param array array will be sorted
     */
    void sort(T[] array);
}
