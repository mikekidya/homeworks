package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSorterTest {

    @Test
    public void sortSorted() {
        Integer[] array = {1, 2, 3, 6, 7};
        Integer[] sorted = {1, 2, 3, 6, 7};
        Sorter<Integer> sorter = new InsertionSorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortReverse() {
        Integer[] array = {9, 5, 5, 3, 2, 1};
        Integer[] sorted = {1, 2, 3, 5, 5, 9};
        Sorter<Integer> sorter = new InsertionSorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortMonotonous() {
        Integer[] array = {1, 1, 1, 1, 1, 1, 1};
        Integer[] sorted = {1, 1, 1, 1, 1, 1, 1};
        Sorter<Integer> sorter = new InsertionSorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }
}