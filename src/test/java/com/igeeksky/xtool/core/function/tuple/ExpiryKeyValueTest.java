package com.igeeksky.xtool.core.function.tuple;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ExpiryKeyValue 测试类
 *
 * @author Patrick.Lau
 * @since 1.0.21
 */
public class ExpiryKeyValueTest {

    private ExpiryKeyValue<String, Integer> expiryKeyValue;

    @BeforeEach
    public void setUp() {
        expiryKeyValue = new ExpiryKeyValue<>("key", 123, 1000);
    }

    @Test
    public void mapKey_ValidMapper_ShouldMapKeyCorrectly() {
        ExpiryKeyValue<Integer, Integer> result = expiryKeyValue.mapKey(String::length);

        assertEquals(3, result.getKey());
        assertEquals(123, result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void mapKey_NullMapper_ShouldReturnNullKey() {
        ExpiryKeyValue<String, Integer> result = expiryKeyValue.mapKey(key -> null);

        assertNull(result.getKey());
        assertEquals(123, result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void mapKey_SameTypeMapper_ShouldMapKeyToSameType() {
        ExpiryKeyValue<String, Integer> result = expiryKeyValue.mapKey(String::toUpperCase);

        assertEquals("KEY", result.getKey());
        assertEquals(123, result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void mapKey_ExceptionInMapper_ShouldPropagateException() {
        RuntimeException exception = null;
        try {
            expiryKeyValue.mapKey((Function<String, String>) key -> {
                throw new IllegalStateException("Test exception");
            });
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    void mapKey_ExceptionInMapper_ShouldPropagateException_1() {
        assertThrows(IllegalStateException.class, () -> expiryKeyValue.mapKey((Function<String, String>) key -> {
            throw new IllegalStateException("Test exception");
        }));
    }

    @Test
    public void getTtl_ShouldReturnCorrectTtl() {
        assertEquals(1000, expiryKeyValue.getTtl());
    }

    @Test
    public void mapValue_ValidMapper_ShouldMapValueCorrectly() {
        ExpiryKeyValue<String, String> result = expiryKeyValue.mapValue(value -> String.valueOf(value * 2));

        assertEquals("key", result.getKey());
        assertEquals("246", result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void mapValue_NullMapper_ShouldReturnNullValue() {
        ExpiryKeyValue<String, Integer> result = expiryKeyValue.mapValue(value -> null);

        assertEquals("key", result.getKey());
        assertNull(result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void mapValue_ExceptionInMapper_ShouldPropagateException() {
        RuntimeException exception = null;
        try {
            expiryKeyValue.mapValue((Function<Integer, Integer>) value -> {
                throw new IllegalStateException("Test exception");
            });
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    public void map_ValidMappers_ShouldMapKeyAndValueCorrectly() {
        ExpiryKeyValue<Integer, String> result = expiryKeyValue.map(String::length, value -> String.valueOf(value * 2));

        assertEquals(3, result.getKey());
        assertEquals("246", result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void map_NullMappers_ShouldReturnNullKeyAndValue() {
        ExpiryKeyValue<String, Integer> result = expiryKeyValue.map(key -> null, value -> null);

        assertNull(result.getKey());
        assertNull(result.getValue());
        assertEquals(1000, result.getTtl());
    }

    @Test
    public void map_ExceptionInMappers_ShouldPropagateException() {
        RuntimeException exception = null;
        try {
            expiryKeyValue.map((Function<String, String>) key -> {
                throw new IllegalStateException("Test exception");
            }, (Function<Integer, Integer>) value -> {
                throw new IllegalStateException("Test exception");
            });
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    public void mapWithTtl_ValidMappersAndTtl_ShouldMapKeyAndValueAndSetTtl() {
        ExpiryKeyValue<Integer, String> result = expiryKeyValue.map(String::length, value -> String.valueOf(value * 2), 2000);

        assertEquals(3, result.getKey());
        assertEquals("246", result.getValue());
        assertEquals(2000, result.getTtl());
    }

    @Test
    public void mapWithTtl_NullMappers_ShouldReturnNullKeyAndValueAndSetTtl() {
        ExpiryKeyValue<String, Integer> result = expiryKeyValue.map(key -> null, value -> null, 2000);

        assertNull(result.getKey());
        assertNull(result.getValue());
        assertEquals(2000, result.getTtl());
    }

    @Test
    public void mapWithTtl_ExceptionInMappers_ShouldPropagateException() {
        RuntimeException exception = null;
        try {
            expiryKeyValue.map((Function<String, String>) key -> {
                throw new IllegalStateException("Test exception");
            }, (Function<Integer, Integer>) value -> {
                throw new IllegalStateException("Test exception");
            }, 2000);
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    public void empty_ShouldReturnEmptyExpiryKeyValue() {
        ExpiryKeyValue<String, Integer> empty = ExpiryKeyValue.empty();

        assertNull(empty.getKey());
        assertNull(empty.getValue());
        assertEquals(0, empty.getTtl());
    }

    @Test
    public void create_ShouldCreateExpiryKeyValueWithCorrectValues() {
        ExpiryKeyValue<String, Integer> created = ExpiryKeyValue.create("newKey", 456, 500);

        assertEquals("newKey", created.getKey());
        assertEquals(456, created.getValue());
        assertEquals(500, created.getTtl());
    }

    @Test
    public void equals_ShouldReturnTrueForEqualObjects() {
        ExpiryKeyValue<String, Integer> another = new ExpiryKeyValue<>("key", 123, 1000);

        assertEquals(expiryKeyValue, another);
    }

    @Test
    public void equals_ShouldReturnFalseForDifferentObjects() {
        ExpiryKeyValue<String, Integer> different = new ExpiryKeyValue<>("key", 123, 2000);

        assertNotEquals(expiryKeyValue, different);
    }

    @Test
    public void hashCode_ShouldReturnCorrectHashCode() {
        int hashCode = expiryKeyValue.hashCode();

        assertEquals(31 * (31 * expiryKeyValue.getKey().hashCode() + Objects.hashCode(expiryKeyValue.getValue())) + Long.hashCode(expiryKeyValue.getTtl()), hashCode);
    }

    @Test
    public void toString_ShouldReturnCorrectStringRepresentation() {
        String toString = expiryKeyValue.toString();

        assertEquals("{\"key\":\"key\", \"value\":123, \"ttl\":1000}", toString);
    }
}
