package com.igeeksky.xtool.core.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igeeksky.xtool.core.domain.Car;
import com.igeeksky.xtool.core.domain.Coder;
import com.igeeksky.xtool.core.domain.Sex;
import com.igeeksky.xtool.core.lang.ByteArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 功能对比测试
 * <p>
 * SimpleJSON vs Jackson
 *
 * @author patrick
 * @since 1.0.10 2024/6/3
 */
class SimpleJSONTest {

    /**
     * 与Jackson对比，两者可能有差异
     * <p>
     * SimpleJSON 仅包含非空值；Jackson 可以通过设置 JsonInclude.Include.NON_NULL 来仅包含非空值
     */
    @Test
    void toJSONString() throws JsonProcessingException {
        String expected1 = "{\"name\":\"李白\",\"age\":18,\"sex\":\"MALE\",\"address\":\"Shenzhen\",\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"id\":\"1\"}}";
        String expected2 = "{\"name\":\"李白\",\"age\":18,\"sex\":\"MALE\",\"address\":\"Shenzhen\",\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"address\":null,\"id\":\"1\",\"car\":null,\"partner\":null}}";

        Coder coder = getCoder();

        // SimpleJSON 序列化结果
        String simple = SimpleJSON.toJSONString(coder);
        System.out.println(simple);
        Assertions.assertEquals(expected1, simple);


        // Jackson 序列化结果 1
        ObjectMapper mapper = getObjectMapper();
        String jackson = mapper.writeValueAsString(coder);
        System.out.println(jackson);
        Assertions.assertEquals(expected1, jackson);


        // Jackson 序列化结果 2
        ObjectMapper mapper2 = new ObjectMapper();
        String jackson2 = mapper2.writeValueAsString(coder);
        System.out.println(jackson2);
        Assertions.assertEquals(expected2, jackson2);
    }

    /**
     * 与Jackson对比，两者有差异（Jackson 的结果多了双引号""）
     */
    @Test
    void toJSONString1() throws JsonProcessingException {
        String expected = "1";
        String expected2 = "\"1\"";

        char c = '1';

        // SimpleJSON 序列化结果
        String simple = SimpleJSON.toJSONString(c);
        System.out.println(simple);
        Assertions.assertEquals(expected, simple);

        // Jackson 序列化结果
        ObjectMapper mapper = new ObjectMapper();
        String jackson = mapper.writeValueAsString(c);
        System.out.println(jackson);
        Assertions.assertEquals(expected2, jackson);
    }

    /**
     * 与Jackson对比，两者有差异（Jackson 的结果多了双引号""）
     */
    @Test
    void toJSONString2() throws JsonProcessingException {
        String expected = Sex.MALE.toString();

        String simple = SimpleJSON.toJSONString(Sex.MALE);
        System.out.println(simple);
        Assertions.assertEquals(expected, simple);

        // Jackson 序列化结果
        ObjectMapper mapper = new ObjectMapper();
        String jackson = mapper.writeValueAsString(Sex.MALE);
        System.out.println(jackson);
        Assertions.assertEquals("\"" + expected + "\"", jackson);
    }

    /**
     * 测试：Map
     */
    @Test
    void toJSONString3() throws JsonProcessingException {
        String expected = "{\"{\\\"name\\\":\\\"2\\\",\\\"age\\\":19,\\\"id\\\":\\\"2\\\"}\":{\"name\":\"2\",\"age\":19,\"id\":\"2\"},\"{\\\"name\\\":\\\"1\\\",\\\"age\\\":18,\\\"id\\\":\\\"1\\\"}\":{\"name\":\"1\",\"age\":18,\"id\":\"1\"}}";

        Map<Coder, Coder> map = new HashMap<>();
        Coder coder1 = new Coder("1", "1", 18);
        map.put(coder1, coder1);

        Coder coder2 = new Coder("2", "2", 19);
        map.put(coder2, coder2);

        // SimpleJSON 序列化结果
        String simple = SimpleJSON.toJSONString(map);
        System.out.println(simple);
        System.out.println("simple.length: " + simple.length());
        Assertions.assertEquals(expected, simple);

        // Jackson 序列化结果
        ObjectMapper mapper = getObjectMapper();
        String jackson = mapper.writeValueAsString(map);
        System.out.println(jackson);
        System.out.println("jackson.length: " + jackson.length());
        Assertions.assertEquals(expected, jackson);
    }

    @Test
    void toJSONString4() {
        String result = SimpleJSON.toJSONString((short) 1);
        System.out.println(result);
        Assertions.assertEquals(String.valueOf(1), result);
    }

    /**
     * 测试：Set
     */
    @Test
    void toJSONString5() throws JsonProcessingException {
        Set<Coder> coders = new LinkedHashSet<>();
        coders.add(new Coder("1", "1", 18));
        coders.add(new Coder("2", "2", 19));

        String expected = "[{\"name\":\"1\",\"age\":18,\"id\":\"1\"},{\"name\":\"2\",\"age\":19,\"id\":\"2\"}]";
        Assertions.assertEquals(expected, SimpleJSON.toJSONString(coders));
        Assertions.assertEquals(expected, getObjectMapper().writeValueAsString(coders));
    }

    /**
     * 测试：数组
     */
    @Test
    void toJSONString6() throws JsonProcessingException {
        Coder coder1 = new Coder("1", "1", 18);
        Coder coder2 = new Coder("2", "2", 19);

        Coder[] array = new Coder[]{coder1, coder2};

        String expected = "[{\"name\":\"1\",\"age\":18,\"id\":\"1\"},{\"name\":\"2\",\"age\":19,\"id\":\"2\"}]";
        Assertions.assertEquals(expected, SimpleJSON.toJSONString(array));
        Assertions.assertEquals(expected, getObjectMapper().writeValueAsString(array));
    }

    @Test
    void toJSONString7() throws JsonProcessingException {
        String expected = "[1,2]";

        int[] array = new int[]{1, 2};

        // SimpleJSON 序列化结果
        String simple = SimpleJSON.toJSONString(array);
        Assertions.assertEquals(expected, simple);

        // Jackson 序列化结果
        ObjectMapper mapper = new ObjectMapper();
        String jackson = mapper.writeValueAsString(array);
        Assertions.assertEquals(expected, jackson);
    }

    /**
     * 注意：
     * <p>
     * SimpleJSON 序列化结果无双引号
     * <p>
     * Jackson 序列化结果有双引号
     */
    @Test
    void toJSONString8() throws JsonProcessingException {
        char[] array = new char[]{'1', '2', 'a'};
        String json = "12a";
        Assertions.assertEquals(json, SimpleJSON.toJSONString(array));

        // Jackson 序列化结果
        String jackson = getObjectMapper().writeValueAsString(array);
        Assertions.assertEquals("\"12a\"", jackson);
    }

    @Test
    void toJSONString11() throws JsonProcessingException {
        String expected = "{\"name\":\"1\",\"age\":18,\"id\":\"1\"}";
        String result = getObjectMapper().writeValueAsString(new Coder("1", "1", 18));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void toJSONString13() throws JsonProcessingException {
        String expected = "{\"name\":\"李白\",\"age\":18,\"sex\":\"MALE\",\"address\":\"Shenzhen\",\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"id\":\"1\"}}";
        Assertions.assertEquals(expected, getCoder().toString());
        Assertions.assertEquals(expected, getObjectMapper().writeValueAsString(getCoder()));
    }

    @Test
    void toJSONString14() {
        String expected = "test";
        StringBuilder builder = new StringBuilder().append(expected);

        Assertions.assertEquals(expected, SimpleJSON.toJSONString(builder));
    }

    /**
     * 测试 null
     */
    @Test
    void toJSONString15() throws JsonProcessingException {
        String expected = "null";

        Assertions.assertEquals(expected, getObjectMapper().writeValueAsString(null));

        Assertions.assertEquals(expected, SimpleJSON.toJSONString(null));
    }

    /**
     * 测试 byte[]
     */
    @Test
    void toJSONString16() throws JsonProcessingException {
        String expected = "{\"value\":\"MTIz\"}";

        ByteArray array = ByteArray.wrap("123".getBytes());

        System.out.println(getObjectMapper().writeValueAsString(array));

        System.out.println(SimpleJSON.toJSONString(array));

        Assertions.assertEquals(expected, SimpleJSON.toJSONString(array));

        Assertions.assertEquals(expected, getObjectMapper().writeValueAsString(array));
    }

    private static Coder getCoder() {
        Coder coder = new Coder("1", "李白", 18, Sex.MALE);
        coder.setAddress("Shenzhen");
        coder.setPartner(new Coder("1", "null", 18, Sex.FEMALE));
        coder.setCar(new Car("BYD", "仰望U8", "Moonlight Silver", 1));
        return coder;
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}