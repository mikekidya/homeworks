package group144.kidyankin;

import java.util.concurrent.*;

/**
 * Class realizing sort based on multi-thread quick sort algorithm.
 *
 * @param <T> Comparable type of array elements
 */
public class ParallelQsorter<T extends Comparable<T>> implements Qsorter<T> {

    /**
     * Sort the array with order given by type default comparator.
     *
     * @param array array will be sorted
     */
    @Override
    public void sort(T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new RecursiveQSort(array, 0, array.length - 1));
    }

    /** Class realizing Recursive Action QSort algorithm */
    private class RecursiveQSort extends RecursiveAction {
        private T[] array;
        private int startIndex;
        private int endIndex;

        /**
         * RecursiveQSort Constructor
         *
         * @param array array with some its part will be sorted
         * @param startIndex start index of this part
         * @param endIndex end index of this part
         */
        public RecursiveQSort(T[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        /** Main method realizing QSort algorithm */
        @Override
        public void compute() {
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
            RecursiveQSort leftPart = new RecursiveQSort(array, startIndex, i - 1);
            RecursiveQSort rightPart = new RecursiveQSort(array, i, endIndex);

            rightPart.fork();
            leftPart.compute();
            rightPart.join();

        }
    }

}
