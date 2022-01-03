package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;
import lombok.Value;

import java.util.List;
import java.util.function.Function;

@Value
public
class ValidationMapping<T, U> implements Validator<T> {
    Function<T, U> mapping;
    Validator<U> validator;

    @Override
    public void validate(T object, List<String> messages) {
        final U o = mapping.apply(object);
        validator.validate(o, messages);
    }

}
