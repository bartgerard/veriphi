package be.gerard.validiphi;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * CollectionValidations
 *
 * @author bartgerard
 * @version v0.0.1
 */
public final class CollectionValidations {

    private CollectionValidations() {
    }

    public static <T> Predicate<Collection<T>> required() {
        return value -> !value.isEmpty();
    }

    public static <T> Predicate<Collection<T>> empty() {
        return Collection::isEmpty;
    }

    public static <T> Predicate<Collection<T>> length(int length) {
        return value -> value != null && value.size() == length;
    }

    public static <T> Predicate<Collection<T>> min(int min) {
        return value -> value != null && value.size() >= min;
    }

    public static <T> Predicate<Collection<T>> max(int max) {
        return value -> value != null && value.size() <= max;
    }

}
