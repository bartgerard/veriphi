package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;

import java.util.List;

public class FirstOnly<T> implements Validator<T> {

    private final List<Validator<T>> validators;

    public FirstOnly(
            List<Validator<T>> validators
    ) {
        this.validators = validators;
    }

    @Override
    public void validate(
            T object,
            List<String> messages
    ) {
        final int initialSize = messages.size();

        for (final Validator<T> validator : validators) {
            validator.validate(object, messages);

            if (messages.size() > initialSize) {
                return;
            }
        }
    }

}
