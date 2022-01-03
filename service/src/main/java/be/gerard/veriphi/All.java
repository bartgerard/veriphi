package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;
import lombok.Value;

import java.util.List;

@Value
public
class All<T> implements Validator<T> {

    List<Validator<T>> validators;

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
