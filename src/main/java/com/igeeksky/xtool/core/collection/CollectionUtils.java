package com.igeeksky.xtool.core.collection;

import java.util.Collection;


/**
 * 集合工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 判断集合是否为空或不含任何元素
     *
     * @param collection 集合
     * @return 如果集合为空或不含任何元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断集合是否不为空且至少含一个元素
     *
     * @param collection 集合
     * @return 如果集合不为空且至少含一个元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 拼接多个集合
     * <p>
     * 采用第一个集合来保存其它集合的元素，
     * 因此第一个集合不能为 Collections.singletonList() || Collections.emptyList() ……等无法添加元素的集合
     *
     * @param collections 多个集合（集合可以不含元素，但不能为空对象）
     * @param <E>         值类型
     * @return 第一个集合（包含其它集合所有元素）
     */
    @SafeVarargs
    public static <E> Collection<E> concat(Collection<E>... collections) {
        Collection<E> first = collections[0];
        for (int i = 1; i < collections.length; i++) {
            first.addAll(collections[i]);
        }
        return first;
    }
}