package group144.kidyankin;

import java.util.Random;

public class BogoSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(T[] array) {
        while (!isSorted(array)) {
            shuffle(array);
        }
    }

    private void shuffle(T[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int j = random.nextInt(array.length);
            T tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    private boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }
}
