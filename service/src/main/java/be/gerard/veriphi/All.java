package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;

import java.util.List;

public class All<T> implements Validator<T> {

    private final List<Validator<T>> validators;

    public All(
            final List<Validator<T>> validators
    ) {
        this.validators = validators;
    }

    @Override
    public void validate(
            T object,
            List<String> messages
    ) {
        for (final Validator<T> validator : validators) {
            validator.validate(object, messages);
        }
    }

}
