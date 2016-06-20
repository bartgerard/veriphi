package be.gerard.validiphi;

import org.junit.Assert;
import org.junit.Test;

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
public class ValidationsTest {

    @Test
    public void testStrings() {
        Assert.assertTrue(Validations.is("A").test("A"));
        Assert.assertFalse(Validations.is("A").test("B"));

        Assert.assertTrue(Validations.not("A").test("B"));
        Assert.assertFalse(Validations.not("A").test("A"));

        Assert.assertTrue(Validations.Strings.empty().test(""));
        Assert.assertTrue(Validations.Strings.empty().test("     "));
        Assert.assertFalse(Validations.Strings.empty().test("NOT EMPTY"));

        Assert.assertTrue(Validations.Strings.required().test("REQUIRED"));
        Assert.assertFalse(Validations.Strings.required().test(""));
        Assert.assertFalse(Validations.Strings.required().test("     "));

        Assert.assertTrue(Validations.Strings.length(1).test("1"));
        Assert.assertTrue(Validations.Strings.length(4).test("FOUR"));
        Assert.assertFalse(Validations.Strings.length(5).test("FIVE"));

        Assert.assertTrue(Validations.Strings.max(5).test("FOUR"));
        Assert.assertTrue(Validations.Strings.max(8).test("INCLUDED"));
        Assert.assertFalse(Validations.Strings.max(1).test("TOO LONG"));

        Assert.assertTrue(Validations.Strings.min(16).test("JUST LONG ENOUGH"));
        Assert.assertFalse(Validations.Strings.min(16).test("NOT LONG ENOUGH"));

        Assert.assertTrue(Validations.in("A", "B", "C").test("A"));
        Assert.assertFalse(Validations.in("A", "B", "C").test("D"));

        Assert.assertTrue(Validations.not("A", "B", "C").test("D"));
        Assert.assertFalse(Validations.not("A", "B", "C").test("A"));
    }

    @Test
    public void testCollections() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4);

        Assert.assertTrue(Validations.Collections.required().test(list1));
        Assert.assertFalse(Validations.Collections.required().test(Collections.emptySet()));

        Assert.assertFalse(Validations.Collections.empty().test(list1));
        Assert.assertTrue(Validations.Collections.empty().test(Collections.emptySet()));

        Assert.assertTrue(Validations.Collections.length(3).test(list1));
        Assert.assertFalse(Validations.Collections.length(3).test(list2));

        Assert.assertTrue(Validations.Collections.max(3).test(list1));
        Assert.assertFalse(Validations.Collections.max(3).test(list2));

        Assert.assertFalse(Validations.Collections.min(4).test(list1));
        Assert.assertTrue(Validations.Collections.min(4).test(list2));
    }

    @Test
    public void testDates() {
        LocalDate date1 = LocalDate.of(1988, Month.MAY, 3);
        LocalDate date2 = LocalDate.of(1996, Month.DECEMBER, 26);

        Assert.assertTrue(Validations.Dates.is(LocalDate.of(1988, Month.MAY, 3)).test(date1));
        Assert.assertFalse(Validations.Dates.is(LocalDate.of(1988, Month.MAY, 3)).test(date2));

        Assert.assertTrue(Validations.Dates.before(date2).test(date1));
        Assert.assertFalse(Validations.Dates.before(date1).test(date2));

        Assert.assertTrue(Validations.Dates.after(date1).test(date2));
        Assert.assertFalse(Validations.Dates.after(date2).test(date1));

        Assert.assertTrue(Validations.Dates.beforeOr(date1).test(date1));
        Assert.assertTrue(Validations.Dates.beforeOr(date2).test(date1));
        Assert.assertFalse(Validations.Dates.beforeOr(date1).test(date2));

        Assert.assertTrue(Validations.Dates.afterOr(date1).test(date1));
        Assert.assertTrue(Validations.Dates.afterOr(date1).test(date2));
        Assert.assertFalse(Validations.Dates.afterOr(date2).test(date1));
    }

    @Test
    public void testTimes() {
        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(12, 0, 0, 1);

        Assert.assertTrue(Validations.Times.is(LocalTime.of(10, 0)).test(time1));
        Assert.assertFalse(Validations.Times.is(LocalTime.of(10, 0)).test(time2));

        Assert.assertTrue(Validations.Times.before(time2).test(time1));
        Assert.assertFalse(Validations.Times.before(time1).test(time2));

        Assert.assertTrue(Validations.Times.after(time1).test(time2));
        Assert.assertFalse(Validations.Times.after(time2).test(time1));

        Assert.assertTrue(Validations.Times.beforeOr(time1).test(time1));
        Assert.assertTrue(Validations.Times.beforeOr(time2).test(time1));
        Assert.assertFalse(Validations.Times.beforeOr(time1).test(time2));

        Assert.assertTrue(Validations.Times.afterOr(time1).test(time1));
        Assert.assertTrue(Validations.Times.afterOr(time1).test(time2));
        Assert.assertFalse(Validations.Times.afterOr(time2).test(time1));
    }

    @Test
    public void testDateTimes() {
        LocalDateTime dateTime1 = LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 1);

        Assert.assertTrue(Validations.DateTimes.is(LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0)).test(dateTime1));
        Assert.assertFalse(Validations.DateTimes.is(LocalDateTime.of(1988, Month.MAY, 3, 0, 0, 0)).test(dateTime2));

        Assert.assertTrue(Validations.DateTimes.before(dateTime2).test(dateTime1));
        Assert.assertFalse(Validations.DateTimes.before(dateTime1).test(dateTime2));

        Assert.assertTrue(Validations.DateTimes.after(dateTime1).test(dateTime2));
        Assert.assertFalse(Validations.DateTimes.after(dateTime2).test(dateTime1));

        Assert.assertTrue(Validations.DateTimes.beforeOr(dateTime1).test(dateTime1));
        Assert.assertTrue(Validations.DateTimes.beforeOr(dateTime2).test(dateTime1));
        Assert.assertFalse(Validations.DateTimes.beforeOr(dateTime1).test(dateTime2));

        Assert.assertTrue(Validations.DateTimes.afterOr(dateTime1).test(dateTime1));
        Assert.assertTrue(Validations.DateTimes.afterOr(dateTime1).test(dateTime2));
        Assert.assertFalse(Validations.DateTimes.afterOr(dateTime2).test(dateTime1));
    }

}
