package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;

import java.util.List;
import java.util.function.Function;

public class ValidationMapping<T, U> implements Validator<T> {
    private final Function<T, U> mapping;
    private final Validator<U> validator;

    public ValidationMapping(
            Function<T, U> mapping,
            Validator<U> validator
    ) {
        this.mapping = mapping;
        this.validator = validator;
    }

    @Override
    public void validate(T object, List<String> messages) {
        final U o = mapping.apply(object);
        validator.validate(o, messages);
    }

}
