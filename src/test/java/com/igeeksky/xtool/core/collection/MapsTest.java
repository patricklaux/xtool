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


package com.igeeksky.xtool.core.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-19
 */
public class MapsTest {

    @Test
    public void isEmpty() {
        Assert.assertTrue(Maps.isEmpty(nullMap()));
        Assert.assertTrue(Maps.isEmpty(emptyMap()));
        Assert.assertFalse(Maps.isEmpty(singletonMap()));
    }

    @Test
    public void isNotEmpty() {
        Assert.assertFalse(Maps.isNotEmpty(nullMap()));
        Assert.assertFalse(Maps.isNotEmpty(emptyMap()));
        Assert.assertTrue(Maps.isNotEmpty(singletonMap()));
    }

    @Test
    public void merge() {
        HashMap<String, String> target = new HashMap<>();
        Map<String, String> merge = Maps.merge(target, singletonMap());
        Assert.assertEquals("{a=a}", merge.toString());
    }

    @Test
    public void testMerge() {
        HashMap<String, String> target = new HashMap<>();
        target.put("a", "b");

        Map<String, String> merge = Maps.merge(target, singletonMap());

        Assert.assertEquals("{a=b}", merge.toString());
    }

    @Test
    public void testMerge1() {
        HashMap<String, String> target = new HashMap<>();
        target.put("b", "b");

        Map<String, String> merge = Maps.merge(target, singletonMap());
        Assert.assertEquals("{a=a, b=b}", merge.toString());
    }

    @Test
    public void testMerge2() {
        HashMap<String, String> targetMap = new HashMap<>();
        targetMap.put("a", "a");
        targetMap.put("b", null);

        HashMap<String, String> sourceMap = new HashMap<>();
        sourceMap.put("a", "x");
        sourceMap.put("b", "y");
        sourceMap.put("c", "c");
        sourceMap.put("d", null);

        Map<String, String> merge = Maps.merge(targetMap, sourceMap);

        // 情况一：targetMap 含有 sourceMap 的键 "a"，保留 targetMap 的原值，并不覆盖；
        // 情况二：targetMap 含有 sourceMap 的键 "b"，即使 "b"对应的value为空，依然保留 targetMap 的空值，并不覆盖；
        // 情况三：targetMap 没有 sourceMap 的键 "c"，将 sourceMap 的 c=c 复制到 targetMap
        // 情况四：targetMap 没有 sourceMap 的键 "d"，将 sourceMap 的 d=null 复制到 targetMap
        Assert.assertEquals("{a=a, b=null, c=c, d=null}", merge.toString());
    }

    @Test
    public void getLong() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，值转换为 Long 并返回
        Long value = Maps.getLong(map, "a");
        Long expected = 1000L;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getLong(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getLong(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getLong(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetLong() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        long defaultValue = 99999L;

        // 情形一：map中包含键"a"，值不为空，转换正常，值转换为 Long 并返回
        long value = Maps.getLong(map, "a", defaultValue);
        Assert.assertEquals(1000L, value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getLong(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getLong(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getLong(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value);
    }

    @Test
    public void getInteger() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Integer value = Maps.getInteger(map, "a");
        Integer expected = 1000;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getInteger(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getInteger(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getInteger(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetInteger() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        int defaultValue = 99999;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        int value = Maps.getInteger(map, "a", defaultValue);
        Assert.assertEquals(1000, value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getInteger(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getInteger(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getInteger(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value);
    }

    @Test
    public void getShort() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Short value = Maps.getShort(map, "a");
        Short expected = 1000;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getShort(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getShort(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getShort(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetShort() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        short defaultValue = 9999;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        short value = Maps.getShort(map, "a", defaultValue);
        Assert.assertEquals(1000, value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getShort(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getShort(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getShort(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value);
    }

    @Test
    public void getByte() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "100");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Byte value = Maps.getByte(map, "a");
        Byte expected = 100;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getByte(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getByte(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getByte(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetByte() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "100");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        byte defaultValue = 99;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        byte value = Maps.getByte(map, "a", defaultValue);
        Assert.assertEquals(100, value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getByte(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getByte(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getByte(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value);
    }

    @Test
    public void getDouble() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000000000000.222");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Double value = Maps.getDouble(map, "a");
        Double expected = 10000000000000.222D;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getDouble(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getDouble(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getDouble(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetDouble() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000000000000.222");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        double defaultValue = 99999999.99D;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        double value = Maps.getDouble(map, "a", defaultValue);
        Assert.assertEquals(10000000000000.222D, value, 0.001D);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getDouble(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001D);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getDouble(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001D);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getDouble(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001D);
    }

    @Test
    public void getFloat() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000000000000.222");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Float value = Maps.getFloat(map, "a");
        Float expected = 10000000000000.222F;
        Assert.assertEquals(expected, value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getFloat(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getFloat(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "error"，值不为空，但转换异常，抛出异常
        String message = null;
        try {
            Maps.getFloat(map, "notNumber");
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notNumber\"", message);
    }

    @Test
    public void testGetFloat() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000000000000.222");
        map.put("b", null);
        map.put("notNumber", "notNumber");

        float defaultValue = 99999999.99F;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        float value = Maps.getFloat(map, "a", defaultValue);
        Assert.assertEquals(10000000000000.222F, value, 0.001F);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getFloat(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001F);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getFloat(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001F);

        // 情形四：map中包含键 "notNumber"，值不为空，但转换异常，返回 defaultValue
        value = Maps.getFloat(map, "notNumber", defaultValue);
        Assert.assertEquals(defaultValue, value, 0.001F);
    }


    @Test
    public void getBoolean() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        map.put("b", null);
        map.put("notBoolean", "notBoolean");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Boolean value = Maps.getBoolean(map, "a");
        Assert.assertTrue(value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getBoolean(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getBoolean(map, "c");
        Assert.assertNull(value);

        // 情形四：map中包含含键 "notBoolean"，值不为空，但非布尔值，返回 false
        String message = null;
        try {
            Maps.getBoolean(map, "notBoolean");
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"notBoolean\"", message);
    }

    @Test
    public void testGetBoolean() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        map.put("b", null);
        map.put("notBoolean", "notBoolean");

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        boolean value = Maps.getBoolean(map, "a", false);
        Assert.assertTrue(value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getBoolean(map, "b", false);
        Assert.assertFalse(value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getBoolean(map, "c", false);
        Assert.assertFalse(value);

        // 情形四：map中包含键 "notBoolean"，值不为空，但非布尔值，返回 defaultValue
        value = Maps.getBoolean(map, "notBoolean", true);
        Assert.assertTrue(value);
    }

    @Test
    public void getString() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", null);

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        String value = Maps.getString(map, "a");
        Assert.assertEquals("a", value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getString(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getString(map, "c");
        Assert.assertNull(value);
    }

    @Test
    public void testGetString() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", null);

        String defaultValue = "x";

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        String value = Maps.getString(map, "a", defaultValue);
        Assert.assertEquals("a", value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getString(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getString(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：defaultValue 为空，抛出异常
        String message = null;
        try {
            Maps.getString(map, "a", null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("defaultValue must not be null", message);
    }

    @Test
    public void testGetString1() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1000);
        map.put("b", null);

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        String value = Maps.getString(map, "a");
        Assert.assertEquals("1000", value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getString(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getString(map, "c");
        Assert.assertNull(value);
    }

    @Test
    public void testGetString2() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1000);
        map.put("b", null);

        String defaultValue = "x";

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        String value = Maps.getString(map, "a", defaultValue);
        Assert.assertEquals("1000", value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getString(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getString(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：defaultValue 为空，抛出异常
        String message = null;
        try {
            Maps.getString(map, "a", null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("defaultValue must not be null", message);
    }

    @Test
    public void getValue() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1000);
        map.put("b", null);

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Integer value = Maps.getValue(map, "a");
        Assert.assertEquals(Integer.valueOf(1000), value);

        // 情形二：map中包含键"b"，但值为空，返回 null
        value = Maps.getValue(map, "b");
        Assert.assertNull(value);

        // 情形三：map中不含键"c"，返回 null
        value = Maps.getValue(map, "c");
        Assert.assertNull(value);
    }

    @Test
    public void testGetValue() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1000);
        map.put("b", null);

        Integer defaultValue = 999;

        // 情形一：map中包含键"a"，值不为空，转换正常，转换值并返回
        Integer value = Maps.getValue(map, "a", defaultValue);
        Assert.assertEquals(Integer.valueOf(1000), value);

        // 情形二：map中包含键"b"，但值为空，返回 defaultValue
        value = Maps.getValue(map, "b", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形三：map中不含键"c"，返回 defaultValue
        value = Maps.getValue(map, "c", defaultValue);
        Assert.assertEquals(defaultValue, value);

        // 情形四：defaultValue 为空，抛出异常
        String message = null;
        try {
            Maps.getValue(map, "a", null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("defaultValue must not be null", message);
    }

    private static Map<String, String> nullMap() {
        return null;
    }

    private static Map<String, String> emptyMap() {
        return Collections.emptyMap();
    }

    private static Map<String, String> singletonMap() {
        return Collections.singletonMap("a", "a");
    }
}