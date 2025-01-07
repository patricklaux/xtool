package com.igeeksky.xtool.core;


import com.igeeksky.xtool.core.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KeyValue 测试类
 *
 * @author Patrick.Lau
 * @since 1.0.21
 */
public class KeyValueTest {

    private KeyValue<String, Integer> kv0;
    private KeyValue<String, Integer> kv1;
    private KeyValue<String, Integer> kv2;
    private KeyValue<String, Integer> kv3;
    private KeyValue<String, Integer> kv4;
    private KeyValue<String, Integer> kv5;
    private KeyValue<String, Integer> kv6;
    private KeyValue<String, Integer> kv7;
    private KeyValue<String, byte[]> kv8;
    private KeyValue<String, byte[]> kv9;
    private KeyValue<String, byte[]> kv10;

    @BeforeEach
    public void setUp() {
        kv0 = KeyValue.create("key", 100);
        kv1 = new KeyValue<>("key1", 1);
        kv2 = new KeyValue<>("key1", 1);
        kv3 = new KeyValue<>("key2", 1);
        kv4 = new KeyValue<>("key1", 2);
        kv5 = new KeyValue<>(null, null);
        kv6 = new KeyValue<>(null, null);
        kv7 = new KeyValue<>(null, 1);
        kv8 = new KeyValue<>(null, new byte[0]);
        kv9 = new KeyValue<>(null, new byte[0]);
        kv10 = new KeyValue<>(null, new byte[]{1});
    }

    @Test
    public void getKey_ShouldReturnKey() {
        assertEquals("key", kv0.getKey());
    }

    @Test
    public void getValue_ShouldReturnValue() {
        assertEquals(100, kv0.getValue());
    }

    @Test
    public void mapKey_ShouldMapKeyCorrectly() {
        KeyValue<Integer, Integer> mappedKeyValue = kv0.mapKey(String::length);
        assertEquals(3, mappedKeyValue.getKey());
        assertEquals(100, mappedKeyValue.getValue());
    }

    @Test
    public void mapValue_ShouldMapValueCorrectly() {
        KeyValue<String, String> mappedKeyValue = kv0.mapValue(value -> String.valueOf(value * 2));
        assertEquals("key", mappedKeyValue.getKey());
        assertEquals("200", mappedKeyValue.getValue());
    }

    @Test
    public void map_ShouldMapKeyAndValueCorrectly() {
        KeyValue<Integer, String> mappedKeyValue = kv0.map(
                String::length,
                value -> String.valueOf(value * 2)
        );
        assertEquals(3, mappedKeyValue.getKey());
        assertEquals("200", mappedKeyValue.getValue());
    }

    @Test
    public void hasKey_ShouldReturnTrueWhenKeyIsNotNull() {
        assertTrue(kv0.hasKey());
    }

    @Test
    public void hasKey_ShouldReturnFalseWhenKeyIsNull() {
        KeyValue<String, Integer> emptyKeyValue = KeyValue.empty();
        assertFalse(emptyKeyValue.hasKey());
    }

    @Test
    public void hasValue_ShouldReturnTrueWhenValueIsNotNull() {
        assertTrue(kv0.hasValue());
    }

    @Test
    public void hasValue_ShouldReturnFalseWhenValueIsNull() {
        KeyValue<String, Integer> emptyKeyValue = KeyValue.empty();
        assertFalse(emptyKeyValue.hasValue());
    }

    @Test
    public void empty_ShouldReturnEmptyKeyValue() {
        KeyValue<String, Integer> emptyKeyValue = KeyValue.empty();
        assertNull(emptyKeyValue.getKey());
        assertNull(emptyKeyValue.getValue());
    }

    @Test
    public void create_ShouldCreateKeyValueWithGivenKeyAndValue() {
        KeyValue<String, Integer> createdKeyValue = KeyValue.create("newKey", 200);
        assertEquals("newKey", createdKeyValue.getKey());
        assertEquals(200, createdKeyValue.getValue());
    }

    @Test
    public void equals_ShouldReturnTrueForEqualKeyValue() {
        KeyValue<String, Integer> anotherKeyValue = KeyValue.create("key", 100);
        assertEquals(kv0, anotherKeyValue);
    }

    @Test
    public void equals_ShouldReturnFalseForDifferentKeyValue() {
        KeyValue<String, Integer> differentKeyValue = KeyValue.create("anotherKey", 200);
        assertNotEquals(kv0, differentKeyValue);
    }

    @Test
    public void hashCode_ShouldReturnConsistentHashCode() {
        int hashCode = kv0.hashCode();
        assertEquals(hashCode, kv0.hashCode());
    }

    @Test
    public void toString_ShouldReturnCorrectStringRepresentation() {
        assertEquals("{\"key\":\"key\", \"value\":100}", kv0.toString());
    }

    @Test
    public void equals_NullObject_ReturnsTrue() {
        Assert.isFalse(kv1.equals(null));
    }

    @Test
    public void equals_SameObject_ReturnsTrue() {
        Assert.isTrue(kv1.equals(kv1));
    }

    @Test
    public void equals_DifferentType_ReturnsFalse() {
        Assert.isFalse(kv1.equals(Pair.create("key", 1)));
    }

    @Test
    public void equals_SameKeyValue_ReturnsTrue() {
        Assert.isTrue(kv1.equals(kv2));
    }

    @Test
    public void equals_DifferentKey_ReturnsFalse() {
        Assert.isFalse(kv1.equals(kv3));
    }

    @Test
    public void equals_DifferentValue_ReturnsFalse() {
        Assert.isFalse(kv1.equals(kv4));
    }

    @Test
    public void equals_NullValues_ReturnsTrue() {
        Assert.isTrue(kv5.equals(kv6));
    }

    @Test
    public void equals_OneNullValue_ReturnsFalse() {
        Assert.isFalse(kv5.equals(kv7));
    }

    @Test
    public void equals_byteArrayValue_ReturnsTrue() {
        Assert.isTrue(kv8.equals(kv9));
    }

    @Test
    public void equals_byteArrayValue_ReturnsFalse() {
        Assert.isFalse(kv8.equals(kv10));
    }

}