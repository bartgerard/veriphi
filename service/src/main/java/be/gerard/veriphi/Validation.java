package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Validation<T> implements Validator<T> {
    private final Predicate<T> validationMethod;
    private final String validationMessage;

    public Validation(
            Predicate<T> validationMethod,
            String validationMessage
    ) {
        this.validationMethod = validationMethod;
        this.validationMessage = validationMessage;
    }

    public static <T> Validator<T> validate(
            final Predicate<T> validationMethod,
            final String validationMessage
    ) {
        return new Validation<>(validationMethod, validationMessage);
    }

    public static Validator<String> hasText(final String label) {
        return validate(Objects::nonNull, label);
    }

    public static <T> Validator<T> required(final String label) {
        return validate(Objects::nonNull, label);
    }

    @Override
    public void validate(
            T object,
            List<String> messages
    ) {
        if (!validationMethod.test(object)) {
            messages.add(validationMessage);
        }
    }

}
