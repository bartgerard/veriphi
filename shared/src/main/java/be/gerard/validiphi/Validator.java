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

    public Validator<T> validate(Predicate<T> validation, String message) {
        return null;
    }

    public <V> Validator<T> validate(Function<T, V> projection, Predicate<V> validation, String message) {
        //return validate(t -> validation.test(projection.apply(t)), message);
        return validate(projection.andThen(validation::test)::apply, message);
    }

    public T get() throws IllegalStateException {
        return null;
    }

}
