package group144.kidyankin;

import java.util.function.Supplier;

/**
 * Class providing constructors for Lazy objects
 */
public class LazyFactory {

    /**
     * Creates non-synchronized Lazy object
     *
     * @param function Supplier with actions will be evaluated
     * @param <T> type parameter of returning value of supplier
     * @return non-synchronized Lazy object
     */
    public static <T> Lazy<T> createOneThreadLazy(Supplier<T> function) {
        return new OneThreadLazyHolder<>(function);
    }

    /**
     * Creates synchronized Lazy object
     *
     * @param function Supplier with actions will be evaluated
     * @param <T> type parameter of returning value of supplier
     * @return synchronized Lazy object
     */
    public static <T> Lazy<T> createMultiThreadLazy(Supplier<T> function) {
        return new MultiThreadLazyHolder<>(function);
    }

    /**
     * Class realizing non-synchronized Lazy object
     * @param <T> type parameter of returning value of supplier
     */
    private static class OneThreadLazyHolder<T> implements Lazy<T> {

        private Supplier<T> function;
        private T result = null;

        public OneThreadLazyHolder(Supplier<T> function) {
            this.function = function;
        }

        @Override
        public T get() {
            if (function != null) {
                result = function.get();
                function = null;
            }
            return result;
        }
    }

    /**
     * Class realizing synchronized Lazy object
     * @param <T> type parameter of returning value of supplier
     */
    private static class MultiThreadLazyHolder<T> implements Lazy<T> {

        private volatile Supplier<T> function;
        private volatile T result = null;

        public MultiThreadLazyHolder(Supplier<T> function) {
            this.function = function;
        }

        @Override
        public T get() {
            if (function != null) {
                synchronized (this) {
                    if (function != null) {
                        result = function.get();
                        function = null;
                    }
                }
            }
            return result;
        }
    }
}
