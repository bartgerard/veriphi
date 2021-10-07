package be.gerard.veriphi;

import lombok.Value;

import java.util.List;
import java.util.function.Function;

@Value
public class ValidationMapping<T, U> implements Validator<T> {
    Function<T, U> mapping;
    List<? extends Validator<U>> validations;

    @Override
    public void validate(T object, List<String> messages) {
        final U o = mapping.apply(object);

        for (final Validator<U> validator : validations) {
            validator.validate(o, messages);
        }
    }

}
