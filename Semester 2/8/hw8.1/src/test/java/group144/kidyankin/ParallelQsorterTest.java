package group144.kidyankin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/** Tests for multi-thread QSort algorithm */
public class ParallelQsorterTest {
    @Test
    public void sortRandomTest() {
        Random random = new Random();
        Integer[] array = Stream.generate(() -> (random.nextInt()) % 1000).limit(100).toArray(Integer[]::new);
        Integer[] sortedArray = Stream.of(array).sorted().toArray(Integer[]::new);
        Qsorter<Integer> sorter = new ParallelQsorter<>();
        sorter.sort(array);
        assertArrayEquals(sortedArray, array);
    }

    @Test
    public void sortSortedTest() {
        Integer[] array = {1, 2, 3, 6, 7};
        Integer[] sorted = {1, 2, 3, 6, 7};
        Qsorter<Integer> sorter = new ParallelQsorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortReverseTest() {
        Integer[] array = {9, 5, 5, 3, 2, 1};
        Integer[] sorted = {1, 2, 3, 5, 5, 9};
        Qsorter<Integer> sorter = new ParallelQsorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortMonotonousTest() {
        Integer[] array = {1, 1, 1, 1, 1, 1, 1};
        Integer[] sorted = {1, 1, 1, 1, 1, 1, 1};
        Qsorter<Integer> sorter = new ParallelQsorter<>();
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }
}