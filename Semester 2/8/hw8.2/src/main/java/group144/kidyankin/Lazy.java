package group144.kidyankin;

/**
 * Class realizing lazy evaluation process.
 * @param <T> type parameter of returning value
 */
public interface Lazy<T> {

    /**
     * Returns the value evaluated to Lazy object.
     * Calculate the evaluation if it was not evaluated yet.
     * Returns the previous evaluated value if it was evaluated yet.
     *
     * @return the value evaluated to Lazy object.
     */
    T get();
}
