package group144.kidyankin;

import java.util.Comparator;

/** Class realizing Bubble Sort algorithm */
public class BubbleSorter {

    /**
     * Sorts elements in array with order giver by comparator
     * Realizing Bubble Sort algorithm
     *
     * @param array array will be sorted
     * @param comparator comparator which gives the order of elements will be sorted
     * @param <T> type of sorting elements
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        for (int j = array.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    T tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
        }
    }
}
