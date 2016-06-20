package be.gerard.validiphi;

import java.time.chrono.ChronoLocalDate;
import java.util.function.Predicate;

/**
 * DateValidations
 *
 * @author bartgerard
 * @version v0.0.1
 */
public final class DateValidations {

    private DateValidations() {
    }

    public static <T extends ChronoLocalDate> Predicate<T> is(ChronoLocalDate date) {
        return value -> value != null && date != null && value.isEqual(date);
    }

    public static <T extends ChronoLocalDate> Predicate<T> before(ChronoLocalDate date) {
        return value -> value != null && date != null && value.isBefore(date);
    }

    public static <T extends ChronoLocalDate> Predicate<T> after(ChronoLocalDate date) {
        return value -> value != null && date != null && value.isAfter(date);
    }

}
