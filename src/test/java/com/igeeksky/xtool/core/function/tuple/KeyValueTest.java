package com.igeeksky.xtool.core.function.tuple;


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

    private KeyValue<String, Integer> keyValue;

    @BeforeEach
    public void setUp() {
        keyValue = KeyValue.create("key", 100);
    }

    @Test
    public void getKey_ShouldReturnKey() {
        assertEquals("key", keyValue.getKey());
    }

    @Test
    public void getValue_ShouldReturnValue() {
        assertEquals(100, keyValue.getValue());
    }

    @Test
    public void mapKey_ShouldMapKeyCorrectly() {
        KeyValue<Integer, Integer> mappedKeyValue = keyValue.mapKey(String::length);
        assertEquals(3, mappedKeyValue.getKey());
        assertEquals(100, mappedKeyValue.getValue());
    }

    @Test
    public void mapValue_ShouldMapValueCorrectly() {
        KeyValue<String, String> mappedKeyValue = keyValue.mapValue(value -> String.valueOf(value * 2));
        assertEquals("key", mappedKeyValue.getKey());
        assertEquals("200", mappedKeyValue.getValue());
    }

    @Test
    public void map_ShouldMapKeyAndValueCorrectly() {
        KeyValue<Integer, String> mappedKeyValue = keyValue.map(
                String::length,
                value -> String.valueOf(value * 2)
        );
        assertEquals(3, mappedKeyValue.getKey());
        assertEquals("200", mappedKeyValue.getValue());
    }

    @Test
    public void hasKey_ShouldReturnTrueWhenKeyIsNotNull() {
        assertTrue(keyValue.hasKey());
    }

    @Test
    public void hasKey_ShouldReturnFalseWhenKeyIsNull() {
        KeyValue<String, Integer> emptyKeyValue = KeyValue.empty();
        assertFalse(emptyKeyValue.hasKey());
    }

    @Test
    public void hasValue_ShouldReturnTrueWhenValueIsNotNull() {
        assertTrue(keyValue.hasValue());
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
        assertEquals(keyValue, anotherKeyValue);
    }

    @Test
    public void equals_ShouldReturnFalseForDifferentKeyValue() {
        KeyValue<String, Integer> differentKeyValue = KeyValue.create("anotherKey", 200);
        assertNotEquals(keyValue, differentKeyValue);
    }

    @Test
    public void hashCode_ShouldReturnConsistentHashCode() {
        int hashCode = keyValue.hashCode();
        assertEquals(hashCode, keyValue.hashCode());
    }

    @Test
    public void toString_ShouldReturnCorrectStringRepresentation() {
        assertEquals("{\"key\":\"key\", \"value\":100}", keyValue.toString());
    }

}