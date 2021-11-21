package com.igeeksky.xtool.core.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class BooleanUtilsTest {

    @Test
    public void toBoolean() {
        // 情形一：布尔字符串转Boolean，toBoolean == false
        Boolean toBoolean = BooleanUtils.toBoolean("false");
        Assert.assertFalse(toBoolean);

        // 情形二：空字符串转Boolean，toBoolean == null
        toBoolean = BooleanUtils.toBoolean("");
        Assert.assertNull(toBoolean);

        // 情形三：空白字符串转Boolean，toBoolean == null
        toBoolean = BooleanUtils.toBoolean("   ");
        Assert.assertNull(toBoolean);

        // 情形四：含空白的布尔字符串转Boolean，toBoolean == false
        toBoolean = BooleanUtils.toBoolean("  false  ");
        Assert.assertFalse(toBoolean);

        // 情形五：空对象转Boolean，toBoolean == null
        toBoolean = BooleanUtils.toBoolean(nullObject());
        Assert.assertNull(toBoolean);

        // 情形六：Boolean转Boolean，toBoolean == false
        toBoolean = BooleanUtils.toBoolean(false);
        Assert.assertFalse(toBoolean);

        // 情形七：非布尔字符串转Boolean，异常
        String message = null;
        try {
            BooleanUtils.toBoolean("error");
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"error\"", message);
    }

    private Object nullObject() {
        return null;
    }

    @Test
    public void testToBoolean() {
        // 情形一：布尔字符串转Boolean，toBoolean == false
        boolean toBoolean = BooleanUtils.toBoolean("false", true);
        Assert.assertFalse(toBoolean);

        // 情形二：空字符串转Boolean，toBoolean == defaultValue == true
        toBoolean = BooleanUtils.toBoolean("", true);
        Assert.assertTrue(toBoolean);

        // 情形三：空白字符串转Boolean，toBoolean == defaultValue == true
        toBoolean = BooleanUtils.toBoolean("   ", true);
        Assert.assertTrue(toBoolean);

        // 情形四：含空白的布尔字符串转Boolean，toBoolean == false
        toBoolean = BooleanUtils.toBoolean("  false  ", true);
        Assert.assertFalse(toBoolean);

        // 情形五：空对象转Boolean，toBoolean == defaultValue == true
        toBoolean = BooleanUtils.toBoolean(null, true);
        Assert.assertTrue(toBoolean);

        // 情形六：Boolean转Boolean，toBoolean == false
        toBoolean = BooleanUtils.toBoolean(false, true);
        Assert.assertFalse(toBoolean);

        // 情形七：非布尔字符串转Boolean，toBoolean == defaultValue == true
        toBoolean = BooleanUtils.toBoolean("error", true);
        Assert.assertTrue(toBoolean);
    }
}