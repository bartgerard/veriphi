package be.gerard.veriphi;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Validations
 *
 * @author bartgerard
 * @version v0.0.1
 */
public final class Validations {

    private Validations() {
    }

    public static <T> Predicate<T> is(T object) {
        return value -> Objects.equals(value, object);
    }

    public static <T> Predicate<T> not(T object) {
        return is(object).negate();
    }

    public static <T> Predicate<T> in(T... possibleValues) {
        return in(Arrays.asList(possibleValues));
    }

    public static <T> Predicate<T> in(Collection<T> possibleValues) {
        return possibleValues::contains;
    }

    public static <T> Predicate<T> not(T... invalidValues) {
        return not(Arrays.asList(invalidValues));
    }

    public static <T> Predicate<T> not(Collection<T> invalidValues) {
        return in(invalidValues).negate();
    }

    public static class Strings {

        private Strings() {
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

    public static class Collections {

        private Collections() {
        }

        public static <C extends Collection<?>> Predicate<C> required() {
            return value -> !value.isEmpty();
        }

        public static <C extends Collection<?>> Predicate<C> empty() {
            return Collection::isEmpty;
        }

        public static <C extends Collection<?>> Predicate<C> length(int length) {
            return value -> value != null && value.size() == length;
        }

        public static <C extends Collection<?>> Predicate<C> min(int min) {
            return value -> value != null && value.size() >= min;
        }

        public static <C extends Collection<?>> Predicate<C> max(int max) {
            return value -> value != null && value.size() <= max;
        }

    }

    public static class Dates {

        private Dates() {
        }

        public static Predicate<LocalDate> is(LocalDate date) {
            return value -> value != null && date != null && value.isEqual(date);
        }

        public static Predicate<LocalDate> before(LocalDate date) {
            return value -> value != null && date != null && value.isBefore(date);
        }

        public static Predicate<LocalDate> beforeOr(LocalDate date) {
            return is(date).or(before(date));
        }

        public static Predicate<LocalDate> after(LocalDate date) {
            return value -> value != null && date != null && value.isAfter(date);
        }

        public static Predicate<LocalDate> afterOr(LocalDate date) {
            return is(date).or(after(date));
        }

    }

    public static class Times {

        private Times() {
        }

        public static Predicate<LocalTime> is(LocalTime time) {
            return Validations.is(time);
        }

        public static Predicate<LocalTime> before(LocalTime time) {
            return value -> value != null && time != null && value.isBefore(time);
        }

        public static Predicate<LocalTime> beforeOr(LocalTime time) {
            return is(time).or(before(time));
        }

        public static Predicate<LocalTime> after(LocalTime time) {
            return value -> value != null && time != null && value.isAfter(time);
        }

        public static Predicate<LocalTime> afterOr(LocalTime time) {
            return is(time).or(after(time));
        }

    }

    public static class DateTimes {

        private DateTimes() {
        }

        public static Predicate<LocalDateTime> is(LocalDateTime dateTime) {
            return value -> value != null && dateTime != null && value.isEqual(dateTime);
        }

        public static Predicate<LocalDateTime> before(LocalDateTime dateTime) {
            return value -> value != null && dateTime != null && value.isBefore(dateTime);
        }

        public static Predicate<LocalDateTime> beforeOr(LocalDateTime dateTime) {
            return is(dateTime).or(before(dateTime));
        }

        public static Predicate<LocalDateTime> after(LocalDateTime dateTime) {
            return value -> value != null && dateTime != null && value.isAfter(dateTime);
        }

        public static Predicate<LocalDateTime> afterOr(LocalDateTime dateTime) {
            return is(dateTime).or(after(dateTime));
        }

    }

}
