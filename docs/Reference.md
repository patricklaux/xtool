# xtool-1.0.4 参考文档

Author: [Patrick.Lau](mailto:patricklauxx@gmail.com)        Version: 1.0.4





------

## 1. 自定义注解

### 1.1. @ParameterNames

**记录参数名称**

此注解可用于构造器和方法；运行期可读取。

JDK 1.8 之前不记录构造器和方法的参数名称；JDK 1.8 及之后的版本可以通过在编译时指定 -parameters来记录参数名称（默认不记录），但 Jar 包体积会增大。

如果我们需要反射生成的类是可以确定的，那么可以使用此注解来记录参数名，反射时再根据名称匹配构造器和方法。

```java
public class Pair<K, V> {

    @ParameterNames({"key", "value"})
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // ......
}
```



### 1.2. @Perfect

**完美代码标识**

此注解可用于类、构造器、方法和字段；仅存在于源码。

对于确信已经完美而无需再行修改的代码，可使用此注解进行标识。

软件项目常常会有大量代码，因此需要标注已解决问题，从而让我们可以集中精力去解决未知问题，这个注解正是起到这样的作用。

```java

@Perfect
public class ConcurrentHashTrie<V> implements Trie<V> {
    //......
}
```



------

## 2. 常用工具类

### 2.1. StringUtils

#### 2.1.1. hasText 与 hasLength

**判断字符串是否为空或空白**

[Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) 的 StringUtils 提供了 *isBlank*、*isEmpty* 方法，我在使用时总要想一想仅有空白字符的情况应该用哪个方法。比较而言，*hasText* 和 *hasLength* 的命名会更加直观清晰，因此提供了这两个方法。

> **方法比较**
>
> 情形三：字符串只有空白字符
>
> - StringUtils.hasLength("   ") == true
> - StringUtils.hasText("   ") == false

```java
public class StringUtilsTest {
    @Test
    public void hasText() {
        // 情形一：字符串为空对象，hasText == false
        boolean hasText = StringUtils.hasText(null);
        Assert.assertFalse(hasText);

        // 情形二：字符串无字符，hasText == false
        hasText = StringUtils.hasText("");
        Assert.assertFalse(hasText);

        // 情形三：字符串只有空白字符，hasText == false
        hasText = StringUtils.hasText("   ");
        Assert.assertFalse(hasText);

        // 情形四：字符串有非空白字符，hasText == true
        hasText = StringUtils.hasText("a");
        Assert.assertTrue(hasText);

        // 情形五：字符串有非空白字符，hasText == true
        hasText = StringUtils.hasText(" a ");
        Assert.assertTrue(hasText);
    }

    @Test
    public void hasLength() {
        // 情形一：字符串为空对象，hasLength == false
        boolean hasLength = StringUtils.hasLength(null);
        Assert.assertFalse(hasLength);

        // 情形二：字符串无字符，hasText == false
        hasLength = StringUtils.hasLength("");
        Assert.assertFalse(hasLength);

        // 情形三：字符串只有空白字符，hasLength == true
        hasLength = StringUtils.hasLength("   ");
        Assert.assertTrue(hasLength);

        // 情形四：字符串有非空白字符，hasLength == true
        hasLength = StringUtils.hasLength("a");
        Assert.assertTrue(hasLength);

        // 情形五：字符串有非空白字符，hasLength == true
        hasLength = StringUtils.hasLength(" a ");
        Assert.assertTrue(hasLength);
    }
}
```



#### 2.1.2. trim 与 trimToNull

**去除空白字符**

> **方法比较** 
>
> 情形二：字符串无字符
>
> - StringUtils.trim("") == ""
> - StringUtils.trimToNull("") == null
>
> 情形三：字符串只有空白字符
>
> - StringUtils.trim("    ") == ""
> - StringUtils.trimToNull("    ") == null

```java
public class StringUtilsTest {
    @Test
    public void trim() {
        // 情形一：字符串为空对象，trim == null
        String trim = StringUtils.trim(null);
        Assert.assertNull(trim);

        // 情形二：字符串无字符，trim == ""
        trim = StringUtils.trim("");
        Assert.assertEquals("", trim);

        // 情形三：字符串只有空白字符，trim == ""
        trim = StringUtils.trim("   ");
        Assert.assertEquals("", trim);

        // 情形四：字符串有非空白字符，trim == "a"
        trim = StringUtils.trim("a");
        Assert.assertEquals("a", trim);

        // 情形五：字符串有非空白字符，trim == "a"
        trim = StringUtils.trim(" a ");
        Assert.assertEquals("a", trim);
    }

    @Test
    public void trimToNull() {
        // 情形一：字符串为空对象，trimToNull == null
        String trimToNull = StringUtils.trimToNull(null);
        Assert.assertNull(trimToNull);

        // 情形二：字符串无字符，trimToNull == null
        trimToNull = StringUtils.trimToNull("");
        Assert.assertNull(trimToNull);

        // 情形三：字符串只有空白字符，trimToNull == null
        trimToNull = StringUtils.trimToNull("   ");
        Assert.assertNull(trimToNull);

        // 情形四：字符串有非空白字符，trimToNull == "a"
        trimToNull = StringUtils.trimToNull("a");
        Assert.assertEquals("a", trimToNull);

        // 情形五：字符串有非空白字符，trimToNull == "a"
        trimToNull = StringUtils.trimToNull(" a ");
        Assert.assertEquals("a", trimToNull);
    }
}
```



#### 2.1.3. toUpperCase 与 toLowerCase

**字符串大小写转换**

> **提示**：转大写或转小写之前会先调用 StringUtils.trimToNull() 方法，因此如果字符串没有非空白字符，返回结果为 null

```java
public class StringUtilsTest {
    @Test
    public void toUpperCase() {
        // 情形一：字符串为空对象，upperCase == null
        String upperCase = StringUtils.toUpperCase(null);
        Assert.assertNull(upperCase);

        // 情形二：字符串无字符，upperCase == null
        upperCase = StringUtils.toUpperCase("");
        Assert.assertNull(upperCase);

        // 情形三：字符串只有空白字符，upperCase == null
        upperCase = StringUtils.toUpperCase("   ");
        Assert.assertNull(upperCase);

        // 情形四：字符串有非空白字符，upperCase == "AAA"
        upperCase = StringUtils.toUpperCase("aaa");
        Assert.assertEquals("AAA", upperCase);

        // 情形五：字符串有非空白字符，upperCase == "AAA"
        upperCase = StringUtils.toUpperCase(" aaa ");
        Assert.assertEquals("AAA", upperCase);
    }

    @Test
    public void toLowerCase() {
        // 与toUpperCase 方法类似，只不过是转为小写，略
    }
}
```



#### 2.1.4. capitalize 与 unCapitalize

**字符串的首字符大小写转换**

```java
public class StringUtilsTest {
    @Test
    public void capitalize() {
        // 情形一：字符串为空对象，capitalize == null
        String original = null;
        String capitalize = StringUtils.capitalize(original);
        Assert.assertNull(capitalize);

        // 情形二：字符串无字符，capitalize == ""
        capitalize = StringUtils.capitalize("");
        Assert.assertEquals("", capitalize);

        // 情形三：字符串只有空白字符，capitalize == "   "
        capitalize = StringUtils.capitalize("   ");
        Assert.assertEquals("   ", capitalize);

        // 情形四：字符串有非空白字符，首字符为字母且为小写，capitalize == "Aaa"
        capitalize = StringUtils.capitalize("aaa");
        Assert.assertEquals("Aaa", capitalize);

        // 情形五：字符串有非空白字符，首字符为字母且为大写，capitalize == "Aaa"
        capitalize = StringUtils.capitalize("Aaa");
        Assert.assertEquals("Aaa", capitalize);

        // 情形六：字符串有非空白字符，首字符为非字母，capitalize == " aaa "
        capitalize = StringUtils.capitalize(" aaa ");
        Assert.assertEquals(" aaa ", capitalize);
    }

    @Test
    public void unCapitalize() {
        // 与 capitalize 方法类似，只不过是首字符转小写，略
    }
}
```



### 2.2. NumberUtils

#### 2.2.1. toXXX 转换为数值

如果原对象为空，返回空；如果转换正常，返回转换后的值；如果转换异常，抛出异常信息

```java
/*
 * toLong(Object original) 无默认值，返回值为包装类型；
 * toLong(Object original, long defaultValue) 有默认值，返回值为原始数据类型(捕捉转换异常，异常时返回默认值)。
 */
public class NumberUtilsTest {
    @Test
    public void toLong() {
        Long expected = 123456L;

        // 情形一：数值字符串转Long，toLong == 123456L
        Long toLong = NumberUtils.toLong("123456");
        Assert.assertEquals(expected, toLong);

        // 情形二：空字符串转Long，toLong == null
        toLong = NumberUtils.toLong("");
        Assert.assertNull(toLong);

        // 情形三：空白字符串转Long，toLong == null
        toLong = NumberUtils.toLong("   ");
        Assert.assertNull(toLong);

        // 情形四：含空白的数值字符串转Long，toLong == 123456L
        toLong = NumberUtils.toLong("  123456  ");
        Assert.assertEquals(expected, toLong);

        // 情形五：空对象转Long，toLong == null
        toLong = NumberUtils.toLong(null);
        Assert.assertNull(toLong);

        // 情形六：非数值字符串转Long，异常
        String message = null;
        try {
            toLong = NumberUtils.toLong("error");
            Assert.assertNull(toLong);
        } catch (NumberFormatException e) {
            message = e.getMessage();
        }
        Assert.assertEquals("For input string: \"error\"", message);

        // 情形七：Long转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456L);
        Assert.assertEquals(expected, toLong);

        // 情形八：Integer转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456);
        Assert.assertEquals(expected, toLong);

        // 情形九：Double转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456.1D);
        Assert.assertEquals(expected, toLong);
    }

    @Test
    public void testToLong() {
        long defaultValue = 100000L;

        // 情形一：数值字符串转Long，toLong == 123456L
        long toLong = NumberUtils.toLong("123456", defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形二：空字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形三：空白字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("   ", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形四：含空白的数值字符串转Long，toLong == 123456L
        toLong = NumberUtils.toLong("  123456  ", defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形五：空对象转Long，toLong == defaultValue
        toLong = NumberUtils.toLong(null, defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形六：非数值字符串转Long，toLong == defaultValue
        toLong = NumberUtils.toLong("error", defaultValue);
        Assert.assertEquals(defaultValue, toLong);

        // 情形七：Long转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456L, defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形八：Integer转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456, defaultValue);
        Assert.assertEquals(123456L, toLong);

        // 情形九：Double转Long，toLong == 123456L
        toLong = NumberUtils.toLong(123456.1D, defaultValue);
        Assert.assertEquals(123456L, toLong);
    }
}
```

> **提示**：另外，还有 *toInteger*、*toShort*、 *toByte*、 *toDouble*、 *toFloat* 方法，与 *toLong* 类似，略。



### 2.3. BooleanUtils

#### 2.3.1. toBoolean 转换为布尔值

此方法与 NumberUtils.toXXX() 类似。

```java
/*
 * toBoolean(Object original) 无默认值，返回值为包装类型；
 * toBoolean(Object original, boolean defaultValue) 有默认值，返回值为原始数据类型(捕捉转换异常，异常时返回默认值)
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
        toBoolean = BooleanUtils.toBoolean(null);
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
```



### 2.4. IOUtils

IOUtils 提供了关闭流 和 复制流的静态方法，比较简单，略。

#### 2.4.1. close 关闭流

详见接口文档，略。

#### 2.4.2. copy 复制流

详见接口文档，略。



### 2.5. IOException

关闭流或复制流时如果发生了异常，通常我们什么都不能做，因此用这个类将 java.io.IOException 转换为 RuntimeException。

```java
package com.igeeksky.xtool.core.io;

public class IOException extends RuntimeException {
    // 略
}
```



### 2.6. Assert 断言

#### 2.6.1. 主要方法

- isTrue  判断表达式是否为真
- isFalse  判断表达式是否为假
- notEmpty  判断集合是否为空对象或无元素
- hasText  判断字符串是否不为空且含有非空白字符
- hasLength  判断字符串是否不为空且至少有一个字符（可以为空白字符）
- notNull  判断对象是否不为空

#### 2.6.2. 参数说明

每个方法都支持三种不同的入参，调用者可以自行选择：

- String message 异常提示信息
- Supplier<String> supplier 异常信息提供者（只有在真的发生异常时才调用supplier.get()方法获取异常提示信息，避免产生字符串对象）
- RuntimeException e 自定义的异常

#### 2.6.3. 默认异常类型：

java.lang.IllegalArgumentException

#### 2.6.4. 代码示例

```java
public class AssertTest {
    @Test
    public void isTrue() {
        
        // 异常时使用默认的提示信息
        Assert.isTrue(true);
        
        // 异常时使用传入的提示信息
        Assert.isTrue(true, "error");
        
        // 异常时使用 supplier.get() 获取提示信息
        Assert.isTrue(true, () -> "error");
        
        // 异常时抛出用户传入的异常
        Assert.isTrue(true, new RuntimeException("error"));
    }
    
    @Test
    public void isFalse() {
        // 略
    }
}
```

其它更多信息请参考接口文档，略。



------

## 3. 集合与数组

### 2.1. CollectionUtils

#### 2.1.1. isEmpty 与 isNotEmpty

```java
public class CollectionUtilsTest {
    @Test
    public void testIsEmpty() {

        // 情形一：集合不含元素，isEmpty == true
        boolean isEmpty = CollectionUtils.isEmpty(Collections.emptyList());
        Assert.assertTrue(isEmpty);


        // 情形二：集合为空对象，isEmpty == true
        isEmpty = CollectionUtils.isEmpty(null);
        Assert.assertTrue(isEmpty);


        // 情形三：集合含有至少一个元素，isEmpty == false
        isEmpty = CollectionUtils.isEmpty(Collections.singletonList("a"));
        Assert.assertFalse(isEmpty);
    }
    
    @Test
    public void testIsNotEmpty() {

        // 情形一：集合不含元素，isNotEmpty == false
        boolean isNotEmpty = CollectionUtils.isNotEmpty(Collections.emptyList());
        Assert.assertFalse(isNotEmpty);


        // 情形二：集合为空对象，isNotEmpty == false
        isNotEmpty = CollectionUtils.isNotEmpty(null);
        Assert.assertFalse(isNotEmpty);


        // 情形三：集合含有至少一个元素，isNotEmpty == true
        isNotEmpty = CollectionUtils.isNotEmpty(Collections.singletonList("a"));
        Assert.assertTrue(isNotEmpty);
    }

}
```



#### 2.1.2. concat 多个集合拼接

*concat*(Collection<E>... collections) 方法是可变参数，支持多个集合的拼接。

考虑到大多数情况下，我们需要的都是特定类型的集合，而不是固定的 *LinkedList* 或 *ArrayList*，因此使用用户传入的第一个集合来拼接其它集合。

```java
public class MapsTest {
    @Test
    public void testConcat() {
        List<String> first = new ArrayList<>(2);
        Collection<String> concat = CollectionUtils.concat(first, Collections.singletonList("a"), Collections.singletonList("b"));
        Assert.assertEquals("[a, b]", concat.toString());
    }
}
```

> **注意**：
>
> - 第一个集合如果是 *ArrayList*，请预先计算并指定容量，避免在拼接过程中扩容。
> - 第一个集合不能是 *Collections.singletonList()* 或 *Collections.emptyList()*  之类无法添加元素的集合。



**CollectionUtils** 只有这三个最常用的方法，没有更多了😀！

如果您有某个方法经常要用而又不想写重复代码，欢迎提交 pr 或 issue！



### 2.2. Maps

#### 2.2.1. isEmpty 与 isNotEmpty

其处理逻辑与 **CollectionUtils.isEmpty** 相似，略。



#### 2.2.2. 合并两个 map

sourceMap 的 *key-value* 合并到 targetMap

```java
// 代码示例
public class MapsTest {
    @Test
    public void testMerge() {
        HashMap<String, String> targetMap = new HashMap<>();
        targetMap.put("a", "a");
        targetMap.put("b", null);

        HashMap<String, String> sourceMap = new HashMap<>();
        sourceMap.put("a", "x");
        sourceMap.put("b", "y");
        sourceMap.put("c", "c");
        sourceMap.put("d", null);

        Map<String, String> merge = Maps.merge(targetMap, sourceMap);

        // 情形一：targetMap 含有键 "a"，保留 targetMap 的原值，不覆盖；
        // 情形二：targetMap 含有键 "b"，保留 targetMap 的空值，不覆盖（即使 "b"对应的value为空）；
        // 情形三：targetMap 不含键 "c"，将 sourceMap 的 c=c 复制到 targetMap
        // 情形四：targetMap 不含键 "d"，将 sourceMap 的 d=null 复制到 targetMap（即使 "d"对应的value为空）
        Assert.assertEquals("{a=a, b=null, c=c, d=null}", merge.toString());
    }
}
```

> **注意**：
>
> - 只有 targetMap 没有的key ，才从 sourceMap 中复制到 targetMap ；targetMap 已有的 key，一律保留原值（即使值为空）。
> - targetMap 不能为 *Collections.emptyMap()*、*Collections.singletonMap()* …… 等无法保存元素的 map



#### 2.2.3. 获取Map中的值并转换成目标类型

- **getLong(Map<K,V> map, K key)  无默认值**

从 map 中查找 key 对应的 value，如果 value 不为空，则将该 value 转换为Long并返回；否则，返回 null。

```java
public class MapsTest {
    @Test
    public void testGetLong() {
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
}
```

- **getLong(Map<K,V> map, K key, Long defaultValue) 有默认值**

从 map 中查找 key 对应的 value，如果 value 不为空，则将该 value 转换为Long并返回；否则，返回默认值。

```java
public class MapsTest {
    @Test
    public void testGetLong2() {
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
}
```

> **提示**：
>
> - 另外还有 *getInteger*，*getShort* …… *getString* 等方法，使用方式与 *getLong* 大同小异，略。
>
> - *Maps.getLong* 、*Maps.getInteger* …… 等方法其实是调用了 NumberUtils.toXXX 和 BooleanUtils.toBoolean，因此响应逻辑是一致的。



### 3.3. ArrayUtils

#### 3.3.1. isEmpty 与 isNotEmpty

支持泛型数组和 byte数组，其处理逻辑与 **CollectionUtils.isEmpty** 相似，略。



#### 3.3.2. getFirst与getLast

- **getFirst**：获取数组的第一个元素；
- **getLast**：获取数组的最后一个元素。

```java
public class ArrayUtilsTest {
    // 支持泛型数组和 byte数组
    @Test
    public void getFirst() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assert.assertEquals("a", ArrayUtils.getFirst(array));

        Byte expected = 1;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assert.assertEquals(expected, ArrayUtils.getFirst(bytes));
    }

    @Test
    public void getLast() {
        String[] array = new String[]{"a", "b", "c", "d"};
        Assert.assertEquals("d", ArrayUtils.getLast(array));

        Byte expected = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};
        Assert.assertEquals(expected, ArrayUtils.getLast(bytes));
    }
}
```



#### 3.3.3. concat 多个数组拼接

其处理逻辑与 **CollectionUtils.concat ** 相似，不同点：

- ArrayUtils.concat 返回的是新数组；
- CollectionUtils.concat 返回的是原集合（入参的第一个集合）。

```java
public class ArrayUtilsTest {
    // 支持泛型数组和 byte数组
    @Test
    public void concat() {
        String[] array1 = new String[]{"a", "b", "c", "d"};
        String[] array2 = new String[]{"e", "f", "g", "h"};
        Assert.assertEquals("[a, b, c, d, e, f, g, h]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }

    @Test
    public void testConcat() {
        byte[] array1 = new byte[]{1, 2, 3, 4};
        byte[] array2 = new byte[]{5, 6, 7, 8};
        Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", Arrays.toString(ArrayUtils.concat(array1, array2)));
    }
}
```



## 4. 安全工具类

### 4.1. DigestUtils

**摘要信息工具类**

#### 4.1.1. 支持算法

- MD5
- SHA-1
- SHA-224
- SHA-256
- SHA-384
- SHA-512

#### 4.1.2. 参数说明

- String text 字符串 （或 byte []）
- Charset charset 字符编码（可省略，默认为 StandardCharsets.UTF_8 ）
- boolean lowerCase 是否使用小写字符（可省略，默认为 true ，）

#### 4.1.3. 代码示例

```java
public class DigestUtilsTest {
    // 测试 String text
    @Test
    public void md5() {
        String text = "Less is more";
        
        // 默认字符编码为 StandardCharsets.UTF_8；默认使用小写字符
        String hex = DigestUtils.md5(text);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);

        // 默认使用小写字符
        hex = DigestUtils.md5(text, StandardCharsets.UTF_8);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);

        // 默认字符编码为 StandardCharsets.UTF_8
        hex = DigestUtils.md5(text, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);

        // 指定字符编码为 StandardCharsets.UTF_8；指定使用小写字符
        hex = DigestUtils.md5(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);

        // 指定字符编码为 StandardCharsets.UTF_8；指定使用大写字符
        hex = DigestUtils.md5(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("DF6AE335A4F5CF721002EAA9299F4A9D", hex);
    }

    // 测试 byte[] bytes
    @Test
    public void testMd5() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);

        // 默认使用小写字符
        String hex = DigestUtils.md5(bytes);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
        
        // 指定使用小写字符
        hex = DigestUtils.md5(bytes, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);

        // 指定使用大写字符
        hex = DigestUtils.md5(bytes, false);
        Assert.assertEquals("DF6AE335A4F5CF721002EAA9299F4A9D", hex);
    }
}
```



### 4.2. HexUtils

**16进制字符串工具类**

#### 4.2.1. encodeHex 与 encodeHexStr

- encodeHex 将字节数组转换成16进制的字符数组，返回 char[]
- encodeHexStr 将字节数组转换成16进制的字符串，返回 String

返回结果可参见上一小节的DigestUtilsTest，其它更多信息请参考接口文档，略。



------

## 5. 函数式编程

### 5.1. 元组

Haskell、Scala、Python 等函数式语言都有元组的概念，元组其实可看作是**容量不可变、元素不可变**的列表，但与列表不同的是**元组可以包含不同的元素类型**。

Java 编程当中，有很多时候需要用到一些不可变的定长列表，特别是 key-value，因此这里提供了两种元组。

- **Pair：**元素可以为空，构造方法为 Public（键值对）。
- **Tuple：**元素不能为空，构造方法为 Friendly，必须使用 Tuples 来创建（当前实现了 一元组 至 五元组）。

#### 5.1.1. Pair 与 Pairs

Pairs 是静态工厂类，用于创建 Pair。

##### 5.1.1.1 代码示例

```java
public class PairTest {

    // 获取 key
    @Test
    public void getKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        org.junit.Assert.assertEquals("key", pair.getKey());
    }

    // 获取 value
    @Test
    public void getValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertEquals("value", pair.getValue());
    }

    // 转换 key
    @Test
    public void mapKey() {
        Integer expected = 1;
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<Integer, String> newPair = pair.mapKey((k) -> 1);
        Assert.assertEquals(expected, newPair.getKey());
    }

    // 转换 value
    @Test
    public void mapValue() {
        Integer expected = 1;
        Pair<String, String> pair = Pairs.of("key", "value");
        Pair<String, Integer> newPair = pair.mapValue((k) -> 1);
        Assert.assertEquals(expected, newPair.getValue());
    }

    // key 是否为空
    @Test
    public void hasKey() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertTrue(pair.hasKey());
    }

    // value 是否为空
    @Test
    public void hasValue() {
        Pair<String, String> pair = Pairs.of("key", "value");
        Assert.assertTrue(pair.hasValue());
    }
}
```



#### 5.1.2. Tuple 与 Tuples

Tuples 是静态工厂类，用于创建 Tuple 。

##### 5.1.2.1 代码示例

```java
public class Tuple3Test {

    // 获取第1个元素
    @Test
    public void getT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("a", tuple.getT1());
    }

    // 获取第2个元素
    @Test
    public void getT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("b", tuple.getT2());
    }

    // 获取第3个元素
    @Test
    public void getT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("c", tuple.getT3());
    }

    // 转换第1个元素
    @Test
    public void mapT1() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT1((t1) -> "x").getT1());
    }

    // 转换第2个元素
    @Test
    public void mapT2() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT2((t2) -> "x").getT2());
    }

    // 转换第3个元素
    @Test
    public void mapT3() {
        Tuple3<String, String, String> tuple = Tuples.of("a", "b", "c");
        Assert.assertEquals("x", tuple.mapT3((t3) -> "x").getT3());
    }

    // 元组的元素数量
    @Test
    public void size() {
        Assert.assertEquals(3, Tuples.of("a", "b", "c").size());
    }

    // 转换成数组
    @Test
    public void toArray() {
        String[] expected = new String[]{"a", "b", "c"};
        Object[] actual = Tuples.of("a", "b", "c").toArray();
        int length = Math.max(expected.length, actual.length);
        for (int i = 0; i < length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }
}
```



------

## 7. 数值类

xtool 提供了两个数值类：

- IntegerValue（**！！非原子操作，非线程安全！！**）
- LongValue（**！！非原子操作，非线程安全！！**）

Java 已经有了 AtomicInteger ……等原子操作的数值类型，为什么还要再写这两个类呢？

因为很多时候我们并不需要原子操作，或者说我们可以通过锁来实现一组原子操作，因此并不需要使用 AtomicInteger。

但 Integer 和 Long 类型又没有递增、递减这些方法，因此写了这两个类。

### 7.1. IntegerValue 与 LongValue

#### 7.1.1. 主要方法

- get  获取值
- set  设置新值
- getAndSet  获取旧值，并设置新值
- increment  递增
- incrementAndGet  递增，并返回递增后的新值
- getAndIncrement   递增，并返回递增前的旧值
- decrement  递减
- decrementAndGet  递减，并返回递减后的新值
- getAndDecrement  递减，并返回递减前的旧值

#### 7.1.2. 代码示例

```java
public class IntegerValueTest {
    // 获取值
    @Test
    public void get() {
        IntegerValue integer = new IntegerValue();
        Assert.assertEquals(0, integer.get());
    }

    // 设置新值
    @Test
    public void set() {
        IntegerValue integer = new IntegerValue(100);
        integer.set(1);
        Assert.assertEquals(1, integer.get());
    }

    // 获取旧值，并设置新值
    @Test
    public void getAndSet() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndSet(1);
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(1, integer.get());
    }

    // 递增
    @Test
    public void increment() {
        IntegerValue integer = new IntegerValue(100);
        integer.increment();
        Assert.assertEquals(101, integer.get());
        integer.increment();
        Assert.assertEquals(102, integer.get());
        Assert.assertEquals(102, integer.get());
    }

    // 递增，并返回递增后的新值
    @Test
    public void incrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.incrementAndGet();
        Assert.assertEquals(101, newVal);
        Assert.assertEquals(101, integer.get());
    }

    // 递增，并返回递增前的旧值
    @Test
    public void getAndIncrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndIncrement();
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(101, integer.get());
        oldVal = integer.getAndIncrement();
        Assert.assertEquals(101, oldVal);
        Assert.assertEquals(102, integer.get());
    }

    // 递减
    @Test
    public void decrement() {
        IntegerValue integer = new IntegerValue(100);
        integer.decrement();
        Assert.assertEquals(99, integer.get());
        integer.decrement();
        Assert.assertEquals(98, integer.get());
        Assert.assertEquals(98, integer.get());
    }

    // 递减，并返回递减后的新值
    @Test
    public void decrementAndGet() {
        IntegerValue integer = new IntegerValue(100);
        int newVal = integer.decrementAndGet();
        Assert.assertEquals(99, newVal);
        Assert.assertEquals(99, integer.get());
    }

    // 递减，并返回递减前的旧值
    @Test
    public void getAndDecrement() {
        IntegerValue integer = new IntegerValue(100);
        int oldVal = integer.getAndDecrement();
        Assert.assertEquals(100, oldVal);
        Assert.assertEquals(99, integer.get());
        oldVal = integer.getAndDecrement();
        Assert.assertEquals(99, oldVal);
        Assert.assertEquals(98, integer.get());
    }
}
```



------

## 8. NLP 相关



#### 字典树 ConcurrentHashTrie

