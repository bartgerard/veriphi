package be.gerard.veriphi.api;

import be.gerard.veriphi.All;
import be.gerard.veriphi.FirstOnly;
import be.gerard.veriphi.ValidationMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface Validator<T> {

    default List<String> validate(
            T object
    ) {
        final List<String> messages = new ArrayList<>();
        validate(object, messages);
        return messages;
    }

    void validate(
            T object,
            List<String> messages
    );

    @SafeVarargs
    static <T, U> Validator<T> validating(
            Function<T, U> mapping,
            Validator<U>... validators
    ) {
        return new ValidationMapping<>(
                mapping,
                List.of(validators)
        );
    }

    @SafeVarargs
    static <V> Validator<V> firstOnly(
            Validator<V>... validators
    ) {
        return new FirstOnly<>(
                List.of(validators)
        );
    }

    default Validator<T> thenValidating(
            Validator<T> validator
    ) {
        return new FirstOnly<>(
                List.of(this, validator)
        );
    }

    @SafeVarargs
    static <V> Validator<V> all(
            Validator<V>... validators
    ) {
        return new All<>(
                List.of(validators)
        );
    }

    default Validator<T> and(
            Validator<T> validation
    ) {
        return new All<>(
                List.of(this, validation)
        );
    }

}
