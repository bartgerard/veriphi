package be.gerard.validiphi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Validations
 *
 * @author bartgerard
 * @version v0.0.1
 */
public final class Validations {

    private Validations() {
    }

    public static <T> Predicate<T> is(T object) {
        return value -> Objects.equals(value, object);
    }

    public static <T> Predicate<T> not(T object) {
        return is(object).negate();
    }

    public static <T> Predicate<T> in(T... possibleValues) {
        return in(Arrays.asList(possibleValues));
    }

    public static <T> Predicate<T> in(Collection<T> possibleValues) {
        return possibleValues::contains;
    }

    public static <T> Predicate<T> not(T... invalidValues) {
        return not(Arrays.asList(invalidValues));
    }

    public static <T> Predicate<T> not(Collection<T> invalidValues) {
        return invalidValues::contains;
    }

}
