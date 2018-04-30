package group144.kidyankin;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/** Tests for Lazy objects */
public class LazyFactoryTest {

    @Test
    public void nullOneThreadTest() {
        FunctionWithCounter<Integer> myFunction = new FunctionWithCounter<>(() -> null);
        Lazy<Integer> myLazy = LazyFactory.createOneThreadLazy(myFunction);
        assertEquals(0, myFunction.numberOfCalls());
        assertEquals(null, myLazy.get());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(null, myLazy.get());
    }

    @Test
    public void nullMultiThreadTest() {
        FunctionWithCounter<Integer> myFunction = new FunctionWithCounter<>(() -> null);
        Lazy<Integer> myLazy = LazyFactory.createMultiThreadLazy(myFunction);
        assertEquals(0, myFunction.numberOfCalls());
        assertEquals(null, myLazy.get());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(null, myLazy.get());
    }

    @Test
    public void nullFunctionMultiThreadTest() {
        Lazy<Integer> myLazy = LazyFactory.createMultiThreadLazy(null);
        assertEquals(null, myLazy.get());
        assertEquals(null, myLazy.get());
    }

    @Test
    public void nullFunctionOneThreadTest() {
        Lazy<Integer> myLazy = LazyFactory.createOneThreadLazy(null);
        assertEquals(null, myLazy.get());
        assertEquals(null, myLazy.get());
    }

    @Test
    public void oneThreadLazyTest() {
        FunctionWithCounter<Integer> myFunction = new FunctionWithCounter<>(() -> 42);
        Lazy<Integer> myLazy = LazyFactory.createOneThreadLazy(myFunction);
        assertEquals(0, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
    }


    @Test
    public void oneThreadTestForMultiThreadLazy() {
        FunctionWithCounter<Integer> myFunction = new FunctionWithCounter<>(() -> 42);
        Lazy<Integer> myLazy = LazyFactory.createMultiThreadLazy(myFunction);
        assertEquals(0, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
        assertEquals(42, myLazy.get().intValue());
        assertEquals(1, myFunction.numberOfCalls());
    }

    @Test
    public void multiThreadTestForMultiThreadLazy() throws InterruptedException {
        for (int j = 0; j < 100; j++) {
            FunctionWithCounter<String> myFunction = new FunctionWithCounter<>(() -> "Hello");
            Lazy<String> myLazy = LazyFactory.createMultiThreadLazy(myFunction);
            List<String> answers = Collections.synchronizedList(new ArrayList<>());
            Thread[] threads = new Thread[100];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> answers.add(myLazy.get()));
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            assertEquals(100, answers.parallelStream().filter((s -> s.equals("Hello"))).count());
            assertEquals(1, myFunction.numberOfCalls());
        }

    }

    /**
     * Class realizing Supplier with counter of evaluations.
     *
     * @param <T> type of returning value
     */
    public class FunctionWithCounter<T> implements Supplier<T> {
        private int counter = 0;
        private Supplier<T> function;

        public FunctionWithCounter(Supplier<T> function) {
            this.function = function;
        }

        @Override
        public T get() {
            counter++;
            return function.get();
        }

        /**
         * Returns the number of evaluations.
         *
         * @return the number of evaluations.
         */
        public int numberOfCalls() {
            return counter;
        }
    }

}