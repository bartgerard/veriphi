package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;
import lombok.Value;

import java.util.List;

@Value
public
class FirstOnly<T> implements Validator<T> {

    List<Validator<T>> validators;

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
