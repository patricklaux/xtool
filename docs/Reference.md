# xtool-1.0.4 参考文档

Author: [Patrick.Lau](mailto:patricklauxx@gmail.com)        Version: 1.0.4

## 1. 注解

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

对于确信已经完美而无需再行修改的代码，可以使用此注解进行标识。

软件项目常常会有大量代码，因此需要标注已解决问题，从而让我们可以集中精力去解决未知问题，这个注解正是起到这样的作用。

```java

@Perfect
public class ConcurrentHashTrie<V> implements Trie<V> {
    //......
}
```

## 2. 集合工具类

### 2.1. CollectionUtils

#### 2.1.1. 判断是否为空

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

}
```

#### 2.1.2. 判断是否不为空

```java
public class MapsTest {

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

#### 2.1.3. 多个集合拼接

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

#### 2.2.1. 判断是否为空

使用方法同 **CollectionUtils**，略。

#### 2.2.2. 判断是否不为空

使用方法同 **CollectionUtils**，略。

#### 2.2.3. 合并两个 map

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

#### 2.2.4. 获取Map中的值并转换成目标类型

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

#### 2.2.5. 其它

另，还有其它 *getInteger*，*getShort* …… *getString* 等方法，使用方式与 *getLong* 大同小异，不再赘述。

## 3. 其它工具类

### 3.1. ArrayUtils

### 3.2. BooleanUtils

### 3.3. NumberUtils

### 3.4. StringUtils

### IOUtils

## 4. 数值

### 4.1. LongValue

### 4.2. IntegerValue

## 4. 断言

### Assert

## 5. 函数式编程

### 5.1. 元组

Haskell、Scala等函数式语言都有元组的概念，元组其实可以看成是容量不可变、元素不可变的列表，但与列表不同的是元组的元素类型可以不同。

## 6. NLP 相关

#### 字典树 ConcurrentHashTrie

