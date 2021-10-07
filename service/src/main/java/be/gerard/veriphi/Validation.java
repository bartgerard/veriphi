package be.gerard.veriphi;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class Validation<T> implements Validator<T> {
    private final Predicate<T> a;
    private final String message;
    private final Validator<T> nextValidator;

    public Validation(Predicate<T> a, String message) {
        this(a, message, null);
    }

    public static Validation<String> hasText(final String label) {
        return new Validation<>(Objects::nonNull, label);
    }

    public static <T> Validation<T> required(final String label) {
        return new Validation<>(Objects::nonNull, label);
    }

    public Validation<T> thenValidating(
            final Validator<T> validator
    ) {
        return new Validation<>(a, message, validator);
    }

    @Override
    public void validate(
            T object,
            List<String> messages
    ) {
        if (!a.test(object)) {
            messages.add(message);
        } else {
            nextValidator.validate(object, messages);
        }
    }

}
