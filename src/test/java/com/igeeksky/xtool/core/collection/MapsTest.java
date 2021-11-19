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
    public void getLong() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        Long expected = 1000L;
        Long value = Maps.getLong(map, "a");
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetLong() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Long expected = 1000L;
        Long value = Maps.getLong(map, "b", 1000L);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetLong1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Long expected = 10000L;
        Long value = Maps.getLong(map, "a", 1000L);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetLong2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Long value = Maps.getLong(map, "b", null);
        Assert.assertNull(value);
    }

    @Test
    public void getInteger() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        Integer expected = 1000;
        Integer value = Maps.getInteger(map, "a");
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetInteger() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Integer expected = 1000;
        Integer value = Maps.getInteger(map, "b", 1000);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetInteger1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Integer expected = 10000;
        Integer value = Maps.getInteger(map, "a", 1000);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetInteger2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Integer value = Maps.getInteger(map, "b", null);
        Assert.assertNull(value);
    }

    @Test
    public void getShort() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1000");
        Short expected = 1000;
        Short value = Maps.getShort(map, "a");
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetShort() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Short expected = 1000;
        Short value = Maps.getShort(map, "b", (short) 1000);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetShort1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Short expected = 10000;
        Short value = Maps.getShort(map, "a", (short) 1000);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetShort2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Short value = Maps.getShort(map, "b", null);
        Assert.assertNull(value);
    }

    @Test
    public void getByte() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "100");
        Byte expected = 100;
        Byte value = Maps.getByte(map, "a");
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetByte() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10");
        Byte expected = 100;
        Byte value = Maps.getByte(map, "b", (byte) 100);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetByte1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "100");
        Byte expected = 100;
        Byte value = Maps.getByte(map, "a", (byte) 1000);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void testGetByte2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10000");
        Byte value = Maps.getByte(map, "b", null);
        Assert.assertNull(value);
    }

    @Test
    public void getBoolean() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        Boolean value = Maps.getBoolean(map, "a");
        Assert.assertTrue(value);
    }

    @Test
    public void testGetBoolean() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        Boolean value = Maps.getBoolean(map, "b", false);
        Assert.assertFalse(value);
    }

    @Test
    public void testGetBoolean1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        Boolean value = Maps.getBoolean(map, "a", false);
        Assert.assertTrue(value);
    }

    @Test
    public void testGetBoolean2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "true");
        Boolean value = Maps.getBoolean(map, "b", null);
        Assert.assertNull(value);
    }

    @Test
    public void getString() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        String value = Maps.getString(map, "a");
        Assert.assertEquals("a", value);
    }

    @Test
    public void testGetString() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        String value = Maps.getString(map, "a", "b");
        Assert.assertEquals("a", value);
    }

    @Test
    public void testGetString1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        String value = Maps.getString(map, "c", "b");
        Assert.assertEquals("b", value);
    }

    @Test
    public void testGetString2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        String value = Maps.getString(map, "c", null);
        Assert.assertNull(value);
    }

    @Test
    public void testGetString3() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        String value = Maps.getString(map, "a", null);
        Assert.assertEquals("100", value);
    }

    @Test
    public void testGetString4() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        String value = Maps.getString(map, "b", "10000");
        Assert.assertEquals("10000", value);
    }

    @Test
    public void getValue() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        Integer value = Maps.getValue(map, "a");
        Assert.assertEquals(Integer.valueOf(100), value);
    }

    @Test
    public void testGetValue() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        Integer value = Maps.getValue(map, "a", 1000);
        Assert.assertEquals(Integer.valueOf(100), value);
    }

    @Test
    public void testGetValue1() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        Integer value = Maps.getValue(map, "b", 1000);
        Assert.assertEquals(Integer.valueOf(1000), value);
    }

    @Test
    public void testGetValue2() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 100);
        Integer value = Maps.getValue(map, "c", null);
        Assert.assertNull(value);
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