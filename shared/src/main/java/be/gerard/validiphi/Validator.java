package be.gerard.validiphi;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Validator
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class Validator<T> {

    public static <T> Validator<T> of(T t) {
        return null;
    }

    public <U> Validator<T> validate(Function<T, U> projection, Predicate<T> validation, String message) {
        return null;
    }

    public T get() throws IllegalStateException {
        return null;
    }

}
