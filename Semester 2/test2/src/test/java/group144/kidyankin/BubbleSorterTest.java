package group144.kidyankin;

import org.junit.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/** Tests for Bubble Sorter */
public class BubbleSorterTest {

    /** Tests sorting on Integer array with build-in comparator */
    @Test
    public void sortIntTest() {
        Integer[] array = new Integer[]{2, 1, 5, 3, 4, 2};
        Integer[] sorted = new Integer[]{1, 2, 2, 3, 4, 5};
        BubbleSorter.sort(array, Integer::compareTo);
        assertArrayEquals(sorted, array);
    }

    /** Tests reverse sorting on Integer array */
    @Test
    public void sortReverseIntTest() {
        Integer[] array = new Integer[]{2, 1, 5, 3, 4, 2};
        Integer[] sorted = new Integer[]{5, 4, 3, 2, 2, 1};
        BubbleSorter.sort(array, (a, b) -> -a.compareTo(b));
        assertArrayEquals(sorted, array);
    }

    /** Tests sorting on String array with build-in comparator */
    @Test
    public void sortStringTest() {
        String[] array = new String[]{"peter", "piper", "picked", "peck", "pickled", "pepper"};
        String[] sorted = new String[]{"peck", "pepper", "peter", "picked", "pickled", "piper"};
        BubbleSorter.sort(array, String::compareTo);
        assertArrayEquals(sorted, array);
    }

    /** Tests sorting on random Double array with build-in comparator */
    @Test
    public void sortRandomDoubleTest() {
        Random random = new Random();
        Double[] array = Stream.generate(random::nextDouble).limit(10).toArray(Double[]::new);
        Double[] sorted = Stream.of(array).sorted().toArray(Double[]::new);
        BubbleSorter.sort(array, Double::compareTo);
        assertArrayEquals(sorted, array);
    }

    /** Tests sorting on other type array */
    @Test
    public void sortNonBuiltInTest() {

        class Student {
            private String name;
            private int age;

            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }

        Student ds = new Student("Ds", 25);
        Student ab = new Student("Ab", 18);
        Student al = new Student("Al", 48);
        Student zz = new Student("Zz", 3);

        Student[] group = new Student[]{ds, ab, al, zz};
        Student[] sortedByAge = new Student[]{zz, ab, ds, al};

        BubbleSorter.sort(group, Comparator.comparingInt(o -> o.age));
        assertArrayEquals(sortedByAge, group);
    }

    /** Tests sorting on already sorted Integer array with build-in comparator */
    @Test
    public void sortSorted() {
        Integer[] array = {1, 2, 3, 6, 7};
        Integer[] sorted = {1, 2, 3, 6, 7};
        BubbleSorter.sort(array, Integer::compareTo);
        assertArrayEquals(sorted, array);
    }

    /** Tests sorting on reverse sorted Integer array with build-in comparator */
    @Test
    public void sortReverse() {
        Integer[] array = {9, 5, 5, 3, 2, 1};
        Integer[] sorted = {1, 2, 3, 5, 5, 9};
        BubbleSorter.sort(array, Integer::compareTo);
        assertArrayEquals(sorted, array);
    }

    /** Tests sorting on monotonous Integer array with build-in comparator */
    @Test
    public void sortMonotonous() {
        Integer[] array = {1, 1, 1, 1, 1, 1, 1};
        Integer[] sorted = {1, 1, 1, 1, 1, 1, 1};
        BubbleSorter.sort(array, Integer::compareTo);
        assertArrayEquals(sorted, array);
    }
}

