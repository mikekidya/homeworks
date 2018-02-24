package group144.kidyankin;

public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while ((j > 0) && (array[j].compareTo(array[j - 1]) < 0)) {
                T tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
                j--;
            }
        }
    }
}
