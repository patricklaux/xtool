/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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