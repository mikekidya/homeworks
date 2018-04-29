package group144.kidyankin;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Class realizing comparing between one-thread and multi-threads QSort algorithms.
 */
public class Benchmark {

    private static int ARRAY_SIZE = 1000000; // number of elements in tested arrays
    private static int WARMING_TASKS = 5;    // number of warming tests (not measured)
    private static int BENCHMARK_TASKS = 20; // number of benchmark tests (measured)

    /** Entry point for benchmark test */
    public static void main(String[] args) {
        Qsorter<Integer> parallelQsorter = new ParallelQsorter<>();
        Qsorter<Integer> ordinaryQsorter = new OrdinaryQsorter<>();

        warming(WARMING_TASKS, parallelQsorter, ordinaryQsorter);
        benchmark(BENCHMARK_TASKS, parallelQsorter, ordinaryQsorter);
    }


    /**
     * Generates random array
     *
     * @param length the number of elements in generated array
     * @return random array
     */
    private static Integer[] generateArray(int length) {
        Random random = new Random();
        return Stream.generate(() -> (random.nextInt()) % 1000).limit(length).toArray(Integer[]::new);
    }


    /**
     * Returns the time in nano-sec of sorting
     *
     * @param array array will be sorted
     * @param qsorter QSort algorithm will sort
     * @return the time in nano-sec of sorting
     */
    private static long sortTime(Integer[] array, Qsorter<Integer> qsorter) {
        long startTime = System.nanoTime();
        qsorter.sort(array);
        return System.nanoTime() - startTime;
    }

    /**
     * Method provides first warming test without measuring
     *
     * @param testsNumber number of tests
     * @param parallelQsorter multi-thread QSort algorithm
     * @param ordinaryQsorter one-thread QSort algorithm
     */
    private static void warming(int testsNumber, Qsorter<Integer> parallelQsorter, Qsorter<Integer> ordinaryQsorter) {
        System.out.println("WARMING:");
        for (int i = 1; i <= testsNumber; i++) {
            Integer[] arrayOrdinary = generateArray(ARRAY_SIZE);
            Integer[] arrayParallel = arrayOrdinary.clone();

            System.out.println("Warming " + i + ":");
            System.out.println("Ordinary QSort: " + sortTime(arrayOrdinary, ordinaryQsorter));
            System.out.println("Parallel QSort: " + sortTime(arrayParallel, parallelQsorter));
        }
    }


    /**
     * Method provides benchmark test with measuring and average value
     *
     * @param testsNumber number of tests
     * @param parallelQsorter multi-thread QSort algorithm
     * @param ordinaryQsorter one-thread QSort algorithm
     */
    private static void benchmark(int testsNumber, Qsorter<Integer> parallelQsorter, Qsorter<Integer> ordinaryQsorter) {
        long sumOrdinary = 0;
        long sumParallel = 0;
        System.out.println("BENCHMARK:");
        for (int i = 1; i <= testsNumber; i++) {
            Integer[] arrayOrdinary = generateArray(ARRAY_SIZE);
            Integer[] arrayParallel = arrayOrdinary.clone();

            long ordinaryTime = sortTime(arrayOrdinary, ordinaryQsorter);
            long parallelTime = sortTime(arrayParallel, parallelQsorter);

            sumOrdinary += ordinaryTime;
            sumParallel += parallelTime;

            System.out.println("Benchmark " + i + ":");
            System.out.println("Ordinary QSort: " + ordinaryTime);
            System.out.println("Parallel QSort: " + parallelTime);
        }
        System.out.println("AVERAGE ORDINARY QSORT TIME: " + sumOrdinary / testsNumber);
        System.out.println("AVERAGE PARALLEL QSORT TIME: " + sumParallel / testsNumber);
    }

}
