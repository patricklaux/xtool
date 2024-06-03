package com.igeeksky.xtool.core.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igeeksky.xtool.core.domain.Car;
import com.igeeksky.xtool.core.domain.Coder;
import com.igeeksky.xtool.core.domain.Sex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 性能对比测试
 * <p>
 * SimpleJSON vs Jackson
 *
 * @author patrick
 * @since 1.0.10 2024/6/3
 */
@Disabled
class SimpleJSONPerformanceTest {

    /**
     * SimpleJSON 性能： 100 万次，耗时 2463 毫秒
     * <p>
     * Jackson 性能：100 万次，耗时 1469 毫秒
     * <p>
     */
    @Test
    void toJSONString1() {
        Coder[] array = getCoders();

        String expected = "[{\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"id\":\"1\",\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\"},\"name\":\"1\",\"age\":18,\"address\":\"Shenzhen\"},{\"id\":\"2\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U9\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"id\":\"2\",\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\"},\"name\":\"2\",\"age\":19,\"address\":\"Shenzhen\"}]";
        String simple = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            simple = SimpleJSON.toJSONString(array);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        System.out.println(simple);
        System.out.println(simple.length());

        Assertions.assertEquals(expected, simple);
    }


    /**
     * 耗时：1469 毫秒
     */
    @Test
    void toJSONString2() throws JsonProcessingException {
        Coder[] array = getCoders();

        String expected = "[{\"name\":\"1\",\"age\":18,\"address\":\"Shenzhen\",\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"id\":\"1\"}},{\"name\":\"2\",\"age\":19,\"address\":\"Shenzhen\",\"id\":\"2\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U9\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"id\":\"2\"}}]";
        String jackson = null;

        ObjectMapper mapper = getObjectMapper();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            jackson = mapper.writeValueAsString(array);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        System.out.println(jackson.length());

        System.out.println(jackson);
        Assertions.assertEquals(expected, jackson);
    }

    private static Coder[] getCoders() {
        Coder coder1 = new Coder("1", "1", 18);
        coder1.setAddress("Shenzhen");
        coder1.setPartner(new Coder("1", "null", 18, Sex.FEMALE));
        coder1.setCar(new Car("BYD", "仰望U8", "Moonlight Silver", 1));

        Coder coder2 = new Coder("2", "2", 19);
        coder2.setAddress("Shenzhen");
        coder2.setPartner(new Coder("2", "null", 18, Sex.FEMALE));
        coder2.setCar(new Car("BYD", "仰望U9", "Moonlight Silver", 1));

        return new Coder[]{coder1, coder2};
    }

    /**
     * 耗时：1406 毫秒
     */
    @Test
    void toJSONString3() {
        Coder coder = getCoder();

        String expected = "{\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"id\":\"1\",\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\"},\"name\":\"李白\",\"age\":18,\"sex\":\"MALE\",\"address\":\"Shenzhen\"}";
        String simple = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            simple = SimpleJSON.toJSONString(coder);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");

        Assertions.assertEquals(expected, simple);
    }


    /**
     * 耗时：1000 毫秒
     */
    @Test
    void toJSONString4() throws JsonProcessingException {
        Coder coder = getCoder();

        String expected = "{\"name\":\"李白\",\"age\":18,\"sex\":\"MALE\",\"address\":\"Shenzhen\",\"id\":\"1\",\"car\":{\"brand\":\"BYD\",\"model\":\"仰望U8\",\"color\":\"Moonlight Silver\",\"year\":1},\"partner\":{\"name\":\"null\",\"age\":18,\"sex\":\"FEMALE\",\"id\":\"1\"}}";
        String jackson = null;

        // Jackson 序列化结果 1
        ObjectMapper mapper = getObjectMapper();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            jackson = mapper.writeValueAsString(coder);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");

        Assertions.assertEquals(expected, jackson);
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