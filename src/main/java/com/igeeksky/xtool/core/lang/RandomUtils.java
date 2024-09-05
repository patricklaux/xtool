package com.igeeksky.xtool.core.lang;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 随机工具类
 *
 * @author patrick
 * @since 1.0.9 2024/5/16
 */
public class RandomUtils {

    private static final char[] NUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] LC = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] UC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final char[] LC_UC = ArrayUtils.concat(LC, UC);

    private static final char[] NUM_LC = ArrayUtils.concat(NUM, LC);

    private static final char[] NUM_UC = ArrayUtils.concat(NUM, UC);

    private static final char[] NUM_LC_UC = ArrayUtils.concat(NUM, LC, UC);

    private static final int COUNT = 128;
    private static final int MASK = COUNT - 1;
    private static final Random[] RANDOMS = new Random[COUNT];
    private static final AtomicInteger INDEX = new AtomicInteger(0);

    static {
        for (int i = 0; i < COUNT; i++) {
            RANDOMS[i] = new Random();
        }
    }

    private RandomUtils() {
    }

    private static Random random() {
        return RANDOMS[INDEX.getAndIncrement() & MASK];
    }

    /**
     * 生成随机字符串，字符集：大写字母 和 小写字母
     *
     * @param len 生成的随机字符串长度
     * @return 随机字符串
     */
    public static String nextString(int len) {
        return nextStringPrivate(len, LC_UC);
    }

    /**
     * 生成随机字符串，字符集：大写字母 或 小写字母
     *
     * @param len       生成的随机字符串长度
     * @param lowercase 选择大写字母 或 小写字母
     * @return 随机字符串
     */
    public static String nextString(int len, boolean lowercase) {
        if (lowercase) {
            return nextStringPrivate(len, LC);
        }
        return nextStringPrivate(len, UC);
    }

    /**
     * 生成随机字符串，字符集：数字 + 大写字母 + 小写字母
     *
     * @param len 生成的随机字符串长度
     * @return 随机字符串
     */
    public static String nextStringWithNumber(int len) {
        return nextStringPrivate(len, NUM_LC_UC);
    }

    /**
     * 生成随机字符串，字符集：数字 + (大写字母 或 小写字母)
     *
     * @param len       生成的随机字符串长度
     * @param lowercase 选择大写字母 或 小写字母
     * @return 随机字符串
     */
    public static String nextStringWithNumber(int len, boolean lowercase) {
        if (lowercase) {
            return nextStringPrivate(len, NUM_LC);
        }
        return nextStringPrivate(len, NUM_UC);
    }

    /**
     * 生成随机字符串，使用自定义字符集
     *
     * @param len   生成的随机字符串长度
     * @param chars 自定义字符集（字符集长度必须大于 0）
     * @return 随机字符串
     */
    public static String nextString(int len, char[] chars) {
        Assert.hasLength(chars);
        return nextStringPrivate(len, chars);
    }


    /**
     * 生成随机字符数组，字符集：大写字母 和 小写字母
     *
     * @param len 生成的随机字符数组长度
     * @return 随机字符数组
     */
    public static char[] nextCharArray(int len) {
        return nextCharArrayPrivate(len, LC_UC);
    }

    /**
     * 生成随机字符数组，字符集：大写字母 或 小写字母
     *
     * @param len       生成的随机字符数组长度
     * @param lowercase 选择大写字母 或 小写字母
     * @return 随机字符数组
     */
    public static char[] nextCharArray(int len, boolean lowercase) {
        if (lowercase) {
            return nextCharArrayPrivate(len, LC);
        }
        return nextCharArrayPrivate(len, UC);
    }

    /**
     * 生成随机字符数组，字符集：数字 + 大写字母 和 小写字母
     *
     * @param len 生成的随机字符数组长度
     * @return 随机字符数组
     */
    public static char[] nextCharArrayWithNumber(int len) {
        return nextCharArrayPrivate(len, NUM_LC_UC);
    }

    /**
     * 生成随机字符数组，字符集：数字 + (大写字母 或 小写字母)
     *
     * @param len       生成的随机字符数组长度
     * @param lowercase 选择大写字母 或 小写字母
     * @return 随机字符数组
     */
    public static char[] nextCharArrayWithNumber(int len, boolean lowercase) {
        if (lowercase) {
            return nextCharArrayPrivate(len, NUM_LC);
        }
        return nextCharArrayPrivate(len, NUM_UC);
    }

    /**
     * 生成随机字符数组，使用自定义字符集
     *
     * @param len   生成的随机字符数组长度
     * @param chars 自定义字符集（字符集长度必须大于 0）
     * @return 随机字符数组
     */
    public static char[] nextCharArray(int len, char[] chars) {
        Assert.hasLength(chars);
        return nextCharArrayPrivate(len, chars);
    }

    private static String nextStringPrivate(int len, char[] chars) {
        if (len == 0) return "";
        return new String(nextCharArrayPrivate(len, chars));
    }

    private static char[] nextCharArrayPrivate(int len, char[] chars) {
        Assert.isTrue(len >= 0, "Len must be non-negative.");

        if (len == 0) {
            return new char[0];
        }

        char[] result = new char[len];

        Random random = random();
        for (int i = 0; i < len; i++) {
            result[i] = chars[random.nextInt(chars.length)];
        }

        return result;
    }

    /**
     * 生成指定长度的字节数组
     *
     * @param len 生成的随机字节数组长度
     * @return 随机字节数组
     */
    public static byte[] nextBytes(int len) {
        Assert.isTrue(len >= 0, "Len must be non-negative.");

        byte[] bytes = new byte[len];
        if (len == 0) return bytes;

        random().nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机生成多个字节，并将其填充到用户给定的字节数组
     *
     * @param bytes 用户给定的字节数组
     */
    public static void nextBytes(byte[] bytes) {
        random().nextBytes(bytes);
    }

    /**
     * 随机生成 int 值
     *
     * @return 随机 int 值： 0 &lt;= result &lt; Integer.MAX_VALUE
     */
    public static int nextInt() {
        return random().nextInt(Integer.MAX_VALUE);
    }

    /**
     * 随机生成 int 值
     *
     * @param endExclusive endExclusive &gt; 0
     * @return 随机 int 值： 0 &lt;= result &lt; endExclusive
     */
    public static int nextInt(int endExclusive) {
        return nextInt(0, endExclusive);
    }

    /**
     * 随机生成 int 值
     *
     * @param startInclusive startInclusive &gt;= 0
     * @param endExclusive   endExclusive &gt; 0 &amp;&amp; endExclusive &gt; startInclusive
     * @return 随机 int 值： startInclusive &lt;= result &lt; endExclusive
     */
    public static int nextInt(int startInclusive, int endExclusive) {
        if (startInclusive < 0) {
            throw new IllegalArgumentException("StartInclusive and endExclusive must be non-negative.");
        }
        if (startInclusive >= endExclusive) {
            throw new IllegalArgumentException("EndExclusive must be greater than startInclusive.");
        }
        return random().nextInt(endExclusive - startInclusive) + startInclusive;
    }

    /**
     * 随机生成 long 值
     *
     * @return 随机 long 值： 0 &lt;= result &lt; Long.MAX_VALUE
     */
    public static long nextLong() {
        return random().nextLong(Long.MAX_VALUE);
    }

    /**
     * 随机生成 long 值
     *
     * @param endExclusive endExclusive &gt; 0
     * @return 随机 long 值： 0 &lt;= result &lt; endExclusive
     */
    public static long nextLong(long endExclusive) {
        return random().nextLong(endExclusive);
    }

    /**
     * 随机生成 long 值
     *
     * @param startInclusive startInclusive &gt;= 0
     * @param endExclusive   endExclusive &gt; 0 &amp;&amp; endExclusive &gt; startInclusive
     * @return 随机 long 值： startInclusive &lt;= result &lt; endExclusive
     */
    public static long nextLong(long startInclusive, long endExclusive) {
        if (startInclusive < 0) {
            throw new IllegalArgumentException("StartInclusive and endExclusive must be non-negative.");
        }
        if (startInclusive >= endExclusive) {
            throw new IllegalArgumentException("EndExclusive must be greater than startInclusive.");
        }
        return random().nextLong(endExclusive - startInclusive) + startInclusive;
    }

    /**
     * 随机生成 float 值
     *
     * @return 随机 float 值： 0 &lt;= result &lt;= Float.MAX_VALUE
     */
    public static float nextFloat() {
        return nextFloat(0, Float.MAX_VALUE);
    }

    /**
     * 随机生成 float 值
     *
     * @param endInclusive endInclusive &gt;= 0
     * @return 随机 float 值： 0 &lt;= result &lt;= endInclusive；
     */
    public static float nextFloat(float endInclusive) {
        return nextFloat(0, endInclusive);
    }

    /**
     * 随机生成 float 值
     *
     * @param startInclusive startInclusive &gt;= 0
     * @param endInclusive   endInclusive &gt;= 0 &amp;&amp; endInclusive &gt;= startInclusive
     * @return 随机 float 值： startInclusive &lt;= result &lt;= endInclusive
     */
    public static float nextFloat(float startInclusive, float endInclusive) {
        if (startInclusive < 0) {
            throw new IllegalArgumentException("StartInclusive and endInclusive must be non-negative.");
        }
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("endInclusive must be greater than startInclusive.");
        }
        if (endInclusive == startInclusive) {
            return startInclusive;
        }
        return startInclusive + ((endInclusive - startInclusive) * random().nextFloat());
    }

    /**
     * 随机生成 double 值
     *
     * @return 随机 double 值： 0 &lt;= result &lt;= Double.MAX_VALUE
     */
    public static double nextDouble() {
        return nextDouble(0, Double.MAX_VALUE);
    }

    /**
     * 随机生成 double 值
     *
     * @param endInclusive endInclusive &gt;= 0
     * @return 随机 double 值： 0 &lt;= result &lt;= endInclusive
     */
    public static double nextDouble(double endInclusive) {
        return nextDouble(0, endInclusive);
    }

    /**
     * 随机生成 double 值
     *
     * @param startInclusive startInclusive &gt;= 0
     * @param endInclusive   endInclusive &gt;= 0 &amp;&amp; endInclusive &gt;= startInclusive
     * @return 随机 double 值： startInclusive &lt;= result &lt;= endInclusive
     */
    public static double nextDouble(double startInclusive, double endInclusive) {
        if (startInclusive < 0) {
            throw new IllegalArgumentException("StartInclusive and endInclusive must be non-negative.");
        }
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("endInclusive must be greater than startInclusive.");
        }
        if (endInclusive == startInclusive) {
            return startInclusive;
        }
        return startInclusive + ((endInclusive - startInclusive) * random().nextDouble());
    }

    /**
     * 随机生成 boolean 值
     *
     * @return 随机 boolean 值
     */
    public static boolean nextBoolean() {
        return random().nextBoolean();
    }

}
