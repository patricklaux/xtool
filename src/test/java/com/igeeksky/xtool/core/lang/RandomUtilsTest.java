package com.igeeksky.xtool.core.lang;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author patrick
 * @since 0.0.4 2024/5/16
 */
public class RandomUtilsTest {

    @Test
    public void testNextString0() {
        int len = 17;
        String temp = RandomUtils.nextString(len);
        Assertions.assertEquals(len, temp.length());
        char[] chars = temp.toCharArray();
        for (char c : chars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }
    }

    @Test
    public void testNextString1() {
        int len = 18;
        String lowerStr = RandomUtils.nextString(len, true);

        Assertions.assertEquals(len, lowerStr.length());

        char[] lowerChars = lowerStr.toCharArray();
        for (char c : lowerChars) {
            Assertions.assertTrue(c >= 'a' && c <= 'z');
        }
    }

    @Test
    public void testNextString2() {
        int len = 18;
        String upperStr = RandomUtils.nextString(len, false);

        Assertions.assertEquals(len, upperStr.length());

        char[] upperChars = upperStr.toCharArray();
        for (char c : upperChars) {
            Assertions.assertTrue(c >= 'A' && c <= 'Z');
        }
    }

    @Test
    public void testNextStringWithNumber0() {
        int len = 17;
        String temp = RandomUtils.nextStringWithNumber(len);
        Assertions.assertEquals(len, temp.length());

        char[] chars = temp.toCharArray();
        for (char c : chars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    public void testNextStringWithNumber1() {
        int len = 18;
        String lowerStr = RandomUtils.nextStringWithNumber(len, true);

        Assertions.assertEquals(len, lowerStr.length());

        char[] lowerChars = lowerStr.toCharArray();
        for (char c : lowerChars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    public void testNextStringWithNumber2() {
        int len = 18;
        String upperStr = RandomUtils.nextString(len, false);

        Assertions.assertEquals(len, upperStr.length());

        char[] upperChars = upperStr.toCharArray();
        for (char c : upperChars) {
            Assertions.assertTrue((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    public void testNextString3() {
        int len = 18;
        String str = RandomUtils.nextString(len, new char[]{'a', 'b', 'c', 'd'});
        Assertions.assertEquals(len, str.length());

        char[] chars = str.toCharArray();
        for (char c : chars) {
            Assertions.assertTrue(c >= 'a' && c <= 'd');
        }
    }

    @Test
    public void testNextCharArray0() {
        int len = 18;
        char[] chars = RandomUtils.nextCharArray(len);
        Assertions.assertEquals(len, chars.length);
        for (char c : chars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }
    }

    @Test
    public void testNextCharArray1() {
        int len = 18;
        char[] chars = RandomUtils.nextCharArray(len, true);

        Assertions.assertEquals(len, chars.length);
        for (char c : chars) {
            Assertions.assertTrue(c >= 'a' && c <= 'z');
        }
    }

    @Test
    public void testNextCharArray2() {
        int len = 18;
        char[] chars = RandomUtils.nextCharArray(len, false);

        Assertions.assertEquals(len, chars.length);
        for (char c : chars) {
            Assertions.assertTrue(c >= 'A' && c <= 'Z');
        }
    }

    @Test
    public void testNextCharArray3() {
        int len = 18;
        char[] array = new char[]{'a', 'b', 'c'};
        char[] chars = RandomUtils.nextCharArray(len, array);

        Assertions.assertEquals(len, chars.length);
        for (char c : chars) {
            Assertions.assertTrue(c >= 'a' && c <= 'c');
        }
    }

    @Test
    public void testNextCharArrayWithNumber0() {
        int len = 17;
        char[] chars = RandomUtils.nextCharArrayWithNumber(len);
        Assertions.assertEquals(len, chars.length);

        for (char c : chars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    public void testNextCharArrayWithNumber1() {
        int len = 17;
        char[] chars = RandomUtils.nextCharArrayWithNumber(len, true);
        Assertions.assertEquals(len, chars.length);

        for (char c : chars) {
            Assertions.assertTrue((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    public void testNextCharArrayWithNumber2() {
        int len = 17;
        char[] chars = RandomUtils.nextCharArrayWithNumber(len, false);
        Assertions.assertEquals(len, chars.length);

        for (char c : chars) {
            Assertions.assertTrue((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'));
        }
    }

    @Test
    void nextBytes() {
        int len = 17;
        byte[] bytes = RandomUtils.nextBytes(len);
        Assertions.assertEquals(len, bytes.length);
    }

    @Test
    void testNextBytes() {
        int len = 18;
        byte[] bytes = new byte[len];
        RandomUtils.nextBytes(bytes);
        Assertions.assertEquals(len, bytes.length);
    }

    @Test
    void nextInt() {
        for (int i = 0; i < 10000; i++) {
            int v = RandomUtils.nextInt();
            Assertions.assertTrue(v >= 0);
        }
    }

    @Test
    void testNextInt() {
        for (int i = 0; i < 10000; i++) {
            int endExclusive = 18;
            int v = RandomUtils.nextInt(endExclusive);
            Assertions.assertTrue(v >= 0 && v < endExclusive);
        }
    }

    @Test
    void testNextInt1() {
        for (int i = 0; i < 10000; i++) {
            int startInclusive = 8;
            int endExclusive = 18;
            int v = RandomUtils.nextInt(startInclusive, endExclusive);
            Assertions.assertTrue(v >= startInclusive && v < endExclusive);
        }
    }

    @Test
    void nextLong() {
        for (int i = 0; i < 10000; i++) {
            long v = RandomUtils.nextLong();
            Assertions.assertTrue(v >= 0);
        }
    }

    @Test
    void testNextLong() {
        for (int i = 0; i < 10000; i++) {
            long endExclusive = 18L;
            long v = RandomUtils.nextLong(endExclusive);
            Assertions.assertTrue(v >= 0 && v < endExclusive);
        }
    }

    @Test
    void testNextLong1() {
        for (int i = 0; i < 10000; i++) {
            long startInclusive = 856546L;
            long endExclusive = 189999998L;
            long v = RandomUtils.nextLong(startInclusive, endExclusive);
            Assertions.assertTrue(v >= startInclusive && v < endExclusive);
        }
    }

    @Test
    public void testNextFloat0() {
        float endInclusive = 0;
        for (int i = 0; i < 1000; i++) {
            float v = RandomUtils.nextFloat(endInclusive);
            Assertions.assertTrue(v <= endInclusive);
        }
    }

    @Test
    public void testNextFloat1() {
        float startInclusive = 0.999999F;
        float endInclusive = 0.99999991F;
        for (int i = 0; i < 1000; i++) {
            float v = RandomUtils.nextFloat(startInclusive, endInclusive);
            Assertions.assertTrue(v <= endInclusive && v >= startInclusive);
            if (v == endInclusive) {
                System.out.println(v);
            }
        }
    }

    @Test
    public void testNextFloat2() {
        for (int i = 0; i < 1000; i++) {
            float v = RandomUtils.nextFloat();
            Assertions.assertTrue(v <= Float.MAX_VALUE && v >= 0);
        }
    }

    @Test
    public void nextDouble() {
        double endInclusive = 0.0000000000000009D;
        for (int i = 0; i < 1000; i++) {
            double v = RandomUtils.nextDouble(endInclusive);
            Assertions.assertTrue(v <= endInclusive);
            if (v == endInclusive) {
                System.out.println(v);
            }
        }
    }

    @Test
    public void testNextDouble1() {
        double startInclusive = 0.9999999999999990D;
        double endInclusive = 0.9999999999999999D;
        for (int i = 0; i < 100; i++) {
            double v = RandomUtils.nextDouble(startInclusive, endInclusive);
            Assertions.assertTrue(v <= endInclusive && v >= startInclusive);
        }
    }

    @Test
    public void testNextDouble2() {
        for (int i = 0; i < 1000; i++) {
            double v = RandomUtils.nextDouble();
            Assertions.assertTrue(v <= Double.MAX_VALUE && v >= 0);
        }
    }

    @Test
    void nextBoolean() {
        boolean isTrue = RandomUtils.nextBoolean();
        System.out.println(isTrue);
    }
}