package be.gerard.validiphi;

import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertTrue(StringValidations.empty().test(""));
        Assert.assertTrue(StringValidations.empty().test("     "));
        Assert.assertFalse(StringValidations.empty().test("NOT EMPTY"));

        Assert.assertTrue(StringValidations.required().test("REQUIRED"));
        Assert.assertFalse(StringValidations.required().test(""));
        Assert.assertFalse(StringValidations.required().test("     "));

        Assert.assertTrue(StringValidations.length(1).test("1"));
        Assert.assertTrue(StringValidations.length(4).test("FOUR"));
        Assert.assertFalse(StringValidations.length(5).test("FIVE"));

        Assert.assertTrue(StringValidations.max(5).test("FOUR"));
        Assert.assertTrue(StringValidations.max(8).test("INCLUDED"));
        Assert.assertFalse(StringValidations.max(1).test("TOO LONG"));

        Assert.assertTrue(StringValidations.min(16).test("JUST LONG ENOUGH"));
        Assert.assertFalse(StringValidations.min(16).test("NOT LONG ENOUGH"));

        Assert.assertTrue(Validations.in("A", "B", "C").test("A"));
        Assert.assertFalse(Validations.in("A", "B", "C").test("D"));

        Assert.assertTrue(Validations.not("A", "B", "C").test("D"));
        Assert.assertFalse(Validations.not("A", "B", "C").test("A"));
    }

}
