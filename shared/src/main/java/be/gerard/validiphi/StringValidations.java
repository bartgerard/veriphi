package be.gerard.validiphi;

import org.springframework.util.StringUtils;

import java.util.function.Predicate;

/**
 * StringValidations
 *
 * @author bartgerard
 * @version v0.0.1
 */
public final class StringValidations {

    private StringValidations() {
    }

    public static Predicate<String> required() {
        return StringUtils::hasText;
    }

    public static Predicate<String> empty() {
        return required().negate();
    }

    public static Predicate<String> length(int length) {
        return value -> value != null && value.length() == length;
    }

    public static Predicate<String> min(int min) {
        return value -> value != null && value.length() >= min;
    }

    public static Predicate<String> max(int max) {
        return value -> value != null && value.length() <= max;
    }

}
