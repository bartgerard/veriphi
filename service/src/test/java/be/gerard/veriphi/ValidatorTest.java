package be.gerard.veriphi;

import be.gerard.veriphi.api.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ValidatorTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
class ValidatorTest {

    public static final TestObject TEST_OBJECT_1 = new TestObject(
            "aValue",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2022, 1, 1)
    );

    public static final TestObject TEST_OBJECT_2 = new TestObject(
            null,
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2022, 1, 1)
    );

    public static final TestObject TEST_OBJECT_3 = new TestObject(
            "",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2022, 1, 1)
    );

    @Test
    void test() {
        final List<TestObject> testObjects = List.of(
                TEST_OBJECT_1,
                TEST_OBJECT_2,
                TEST_OBJECT_3
        );

        final List<TestObject> sorted = testObjects.stream()
                .sorted(Comparator.comparing(x -> x.aString, Comparator.nullsFirst(Comparator.naturalOrder()))
                )
                .collect(Collectors.toList());

        final List<String> messages2 = Validator.validating(
                        TestObject::aString,
                        Validator.all(
                                Validation.hasText("test"),
                                Validation.required("test"),
                                Validation.hasText("test")
                        )
                )
                .validate(TEST_OBJECT_2);

        Assertions.assertThat(messages2)
                .isNotEmpty();

        final List<String> messages3 = Validator.validating(
                        TestObject::aString,
                        Validation.hasText("test")
                                .thenValidating(Validation.required("test"))
                                .thenValidating(Validation.hasText("test"))
                )
                .validate(TEST_OBJECT_2);

        Assertions.assertThat(messages3)
                .isNotEmpty();
    }

    record TestObject(
            String aString,
            LocalDate from,
            LocalDate until
    ) {
    }

}
