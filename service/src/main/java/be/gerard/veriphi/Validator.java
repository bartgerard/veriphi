package be.gerard.veriphi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface Validator<T> {

    default List<String> validate(
            T object
    ) {
        final List<String> messages = new ArrayList<>();
        validate(object, messages);
        return messages;
    }

    void validate(
            T object,
            List<String> messages
    );

    //static <T, U> Validation<? super U> validating(
    //        Function<? super T, ? extends U> keyExtractor,
    //        Validation<? super U> keyValidator
    //) {
    //    return keyValidator;
    //}

    @SafeVarargs
    static <T, U> ValidationMapping<T, U> validating(
            Function<T, U> mapping,
            Validator<U>... validations
    ) {
        return new ValidationMapping<>(
                mapping,
                List.of(validations)
        );
    }

    class FistOnly<T> implements Validator<T> {

        @Override
        public void validate(
                T object,
                List<String> messages
        ) {

        }

    }

    class All<T> implements Validator<T> {

        @Override
        public void validate(
                T object,
                List<String> messages
        ) {

        }

    }


}
