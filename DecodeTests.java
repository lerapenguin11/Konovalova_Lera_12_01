import org.junit.Test;

import static org.junit.Assert.*;

public class DecodeTests {

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Integer.decode(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testEmptyString() {
        Integer.decode("");
    }

    @Test(expected = NumberFormatException.class)
    public void testString() {
        Integer.decode("String");
    }

    @Test
    public void testNegativeNumber() {
        assertEquals(-123, (int) Integer.decode("-123"));
    }

    @Test
    public void testPositiveNumber() {
        assertEquals(123, (int) Integer.decode("+123"));
        assertEquals(123, (int) Integer.decode("123"));
    }

    @Test
    public void testZero() {
        assertEquals(0, (int) Integer.decode("0"));
    }

    @Test
    public void testPositiveHexDigits() {
        assertEquals(3871, (int) Integer.decode("0xf1f"));
        assertEquals(476, (int) Integer.decode("0X1dc"));
        assertEquals(241, (int) Integer.decode("#f1"));
    }

    @Test
    public void testNegativeHexDigits() {
        assertEquals(-2802, (int) Integer.decode("-0xAF2"));
        assertEquals(-91, (int) Integer.decode("-0X5B"));
        assertEquals(-241, (int) Integer.decode("-#F1"));
    }

    @Test
    public void testPositiveOctalDigits() {
        assertEquals(36, (int) Integer.decode("044"));
        assertEquals(23, (int) Integer.decode("027"));
        assertEquals(64, (int) Integer.decode("0100"));
    }

    @Test
    public void testNegativeOctalDigits() {
        assertEquals(-172, (int) Integer.decode("-0254"));
        assertEquals(-297, (int) Integer.decode("-0451"));
        assertEquals(-241, (int) Integer.decode("-0361"));
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongSignPosition1() {
        Integer.decode("0x+34");
        Integer.decode("0X+a1");
        Integer.decode("#+77");
        Integer.decode("0+123");
        Integer.decode("0Xa+1");
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongSignPosition2() {
        Integer.decode("0x-55");
        Integer.decode("0X-1b");
        Integer.decode("#-d3");
        Integer.decode("0-634");
        Integer.decode("#d3-3");
    }

    @Test(expected = NumberFormatException.class)
    public void testSpace() {
        Integer.decode("- 123");
        Integer.decode(" 37");
        Integer.decode("# f1");
        Integer.decode("0x f1");
        Integer.decode("0X f1");
        Integer.decode("0 666");
        Integer.decode("555 37");
    }

    @Test
    public void testIntegerMinValue() {
        assertEquals(Integer.MIN_VALUE, (long) Integer.decode(Integer.toString(Integer.MIN_VALUE)));
    }

    @Test
    public void testIntegerMaxValue() {
        assertEquals(Integer.MAX_VALUE, (long) Integer.decode(Integer.toString(Integer.MAX_VALUE)));
    }

    @Test(expected = NumberFormatException.class)
    public void testLong() {
        Integer.decode(Long.toString((long) Integer.MIN_VALUE - 10));
        Integer.decode(Long.toString((long) Integer.MAX_VALUE + 10));
    }
}
