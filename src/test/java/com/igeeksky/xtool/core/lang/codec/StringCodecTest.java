package com.igeeksky.xtool.core.lang.codec;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StringCodec 测试类
 *
 * @author Patrick.Lau
 * @since 1.0.21 2024-12-25
 */
public class StringCodecTest {

    private static StringCodec utf8Codec;

    @BeforeAll
    public static void setUp() {
        utf8Codec = StringCodec.getInstance(StandardCharsets.UTF_8);
    }

    @Test
    public void encode_NullValue_ThrowsCodecException() {
        assertThrows(CodecException.class, () -> utf8Codec.encode(null));
    }

    @Test
    public void encode_ValidString_ReturnsEncodedBytes() {
        String value = "Hello, World!";
        byte[] expectedBytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] actualBytes = utf8Codec.encode(value);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void encode_EmptyString_ReturnsEmptyByteArray() {
        String value = "";
        byte[] expectedBytes = new byte[0];
        byte[] actualBytes = utf8Codec.encode(value);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void decode_NullSource_ThrowsCodecException() {
        assertThrows(CodecException.class, () -> utf8Codec.decode(null, 0, 0));
    }

    @Test
    public void decode_ValidInput_ReturnsCorrectString() {
        byte[] source = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String result = utf8Codec.decode(source, 0, source.length);
        assertEquals("Hello, World!", result);
    }

    @Test
    public void decode_ValidInputWithOffset_ReturnsCorrectString() {
        byte[] source = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String result = utf8Codec.decode(source, 7, 5);
        assertEquals("World", result);
    }

    @Test
    public void decode_EmptySource_ReturnsEmptyString() {
        byte[] source = new byte[0];
        String result = utf8Codec.decode(source, 0, 0);
        assertEquals("", result);
    }

}
