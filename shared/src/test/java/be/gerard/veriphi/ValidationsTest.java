package be.gerard.veriphi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ValidationsTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
class ValidationsTest {

    @Test
    void testStrings() {
        Assertions.assertTrue(Validations.is("A").test("A"));
        Assertions.assertFalse(Validations.is("A").test("B"));

        Assertions.assertTrue(Validations.not("A").test("B"));
        Assertions.assertFalse(Validations.not("A").test("A"));

        Assertions.assertTrue(Validations.Strings.empty().test(""));
        Assertions.assertTrue(Validations.Strings.empty().test("     "));
        Assertions.assertFalse(Validations.Strings.empty().test("NOT EMPTY"));

        Assertions.assertTrue(Validations.Strings.required().test("REQUIRED"));
        Assertions.assertFalse(Validations.Strings.required().test(""));
        Assertions.assertFalse(Validations.Strings.required().test("     "));

        Assertions.assertTrue(Validations.Strings.length(1).test("1"));
        Assertions.assertTrue(Validations.Strings.length(4).test("FOUR"));
        Assertions.assertFalse(Validations.Strings.length(5).test("FIVE"));

        Assertions.assertTrue(Validations.Strings.max(5).test("FOUR"));
        Assertions.assertTrue(Validations.Strings.max(8).test("INCLUDED"));
        Assertions.assertFalse(Validations.Strings.max(1).test("TOO LONG"));

        Assertions.assertTrue(Validations.Strings.min(16).test("JUST LONG ENOUGH"));
        Assertions.assertFalse(Validations.Strings.min(16).test("NOT LONG ENOUGH"));

        Assertions.assertTrue(Validations.in("A", "B", "C").test("A"));
        Assertions.assertFalse(Validations.in("A", "B", "C").test("D"));

        Assertions.assertTrue(Validations.not("A", "B", "C").test("D"));
        Assertions.assertFalse(Validations.not("A", "B", "C").test("A"));
    }

    @Test
    void testCollections() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4);

        Assertions.assertTrue(Validations.Collections.required().test(list1));
        Assertions.assertFalse(Validations.Collections.required().test(Collections.emptySet()));

        Assertions.assertFalse(Validations.Collections.empty().test(list1));
        Assertions.assertTrue(Validations.Collections.empty().test(Collections.emptySet()));

        Assertions.assertTrue(Validations.Collections.length(3).test(list1));
        Assertions.assertFalse(Validations.Collections.length(3).test(list2));

        Assertions.assertTrue(Validations.Collections.max(3).test(list1));
        Assertions.assertFalse(Validations.Collections.max(3).test(list2));

        Assertions.assertFalse(Validations.Collections.min(4).test(list1));
        Assertions.assertTrue(Validations.Collections.min(4).test(list2));
    }

    @Test
    void testDates() {
        LocalDate date1 = LocalDate.of(1988, Month.MAY, 3);
        LocalDate date2 = LocalDate.of(1996, Month.DECEMBER, 26);

        Assertions.assertTrue(Validations.Dates.is(LocalDate.of(1988, Month.MAY, 3)).test(date1));
        Assertions.assertFalse(Validations.Dates.is(LocalDate.of(1988, Month.MAY, 3)).test(date2));

        Assertions.assertTrue(Validations.Dates.before(date2).test(date1));
        Assertions.assertFalse(Validations.Dates.before(date1).test(date2));

        Assertions.assertTrue(Validations.Dates.after(date1).test(date2));
        Assertions.assertFalse(Validations.Dates.after(date2).test(date1));

        Assertions.assertTrue(Validations.Dates.beforeOr(date1).test(date1));
        Assertions.assertTrue(Validations.Dates.beforeOr(date2).test(date1));
        Assertions.assertFalse(Validations.Dates.beforeOr(date1).test(date2));

        Assertions.assertTrue(Validations.Dates.afterOr(date1).test(date1));
        Assertions.assertTrue(Validations.Dates.afterOr(date1).test(date2));
        Assertions.assertFalse(Validations.Dates.afterOr(date2).test(date1));
    }

    @Test
    void testTimes() {
        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(12, 0, 0, 1);

        Assertions.assertTrue(Validations.Times.is(LocalTime.of(10, 0)).test(time1));
        Assertions.assertFalse(Validations.Times.is(LocalTime.of(10, 0)).test(time2));

        Assertions.assertTrue(Validations.Times.before(time2).test(time1));
        Assertions.assertFalse(Validations.Times.before(time1).test(time2));

        Assertions.assertTrue(Validations.Times.after(time1).test(time2));
        Assertions.assertFalse(Validations.Times.after(time2).test(time1));

        Assertions.assertTrue(Validations.Times.beforeOr(time1).test(time1));
        Assertions.assertTrue(Validations.Times.beforeOr(time2).test(time1));
        Assertions.assertFalse(Validations.Times.beforeOr(time1).test(time2));

        Assertions.assertTrue(Validations.Times.afterOr(time1).test(time1));
        Assertions.assertTrue(Validations.Times.afterOr(time1).test(time2));
        Assertions.assertFalse(Validations.Times.afterOr(time2).test(time1));
    }

    @Test
    void testDateTimes() {
        LocalDateTime dateTime1 = LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 1);

        Assertions.assertTrue(Validations.DateTimes.is(LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0)).test(dateTime1));
        Assertions.assertFalse(Validations.DateTimes.is(LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0)).test(dateTime2));

        Assertions.assertTrue(Validations.DateTimes.before(dateTime2).test(dateTime1));
        Assertions.assertFalse(Validations.DateTimes.before(dateTime1).test(dateTime2));

        Assertions.assertTrue(Validations.DateTimes.after(dateTime1).test(dateTime2));
        Assertions.assertFalse(Validations.DateTimes.after(dateTime2).test(dateTime1));

        Assertions.assertTrue(Validations.DateTimes.beforeOr(dateTime1).test(dateTime1));
        Assertions.assertTrue(Validations.DateTimes.beforeOr(dateTime2).test(dateTime1));
        Assertions.assertFalse(Validations.DateTimes.beforeOr(dateTime1).test(dateTime2));

        Assertions.assertTrue(Validations.DateTimes.afterOr(dateTime1).test(dateTime1));
        Assertions.assertTrue(Validations.DateTimes.afterOr(dateTime1).test(dateTime2));
        Assertions.assertFalse(Validations.DateTimes.afterOr(dateTime2).test(dateTime1));
    }

}
