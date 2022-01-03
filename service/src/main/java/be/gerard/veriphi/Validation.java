package be.gerard.veriphi;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class Validation<T> implements Validator<T> {
    private final Predicate<T> validationMethod;
    private final String validationMessage;
    private final Validator<T> nextValidator; // TODO move to firstOnly(..) or all(..)

    public Validation(Predicate<T> validationMethod, String message) {
        this(validationMethod, message, null);
    }

    public static <T> Validation<T> validate(
            final Predicate<T> validationMethod,
            final String validationMessage
    ) {
        return new Validation<>(validationMethod, validationMessage);
    }

    public static Validation<String> hasText(final String label) {
        return validate(Objects::nonNull, label);
    }

    public static <T> Validation<T> required(final String label) {
        return validate(Objects::nonNull, label);
    }

    public Validation<T> thenValidating(
            final Validator<T> validator
    ) {
        return new Validation<>(validationMethod, validationMessage, validator);
    }

    @Override
    public void validate(
            T object,
            List<String> messages
    ) {
        if (!validationMethod.test(object)) {
            messages.add(validationMessage);
        } else {
            nextValidator.validate(object, messages);
        }
    }

}
