package com.igeeksky.xtool.core.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * 集合工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }

    public static <T> Collection<T> concat(Collection<T> a, Collection<T> b) {
        return concat(Arrays.asList(a, b));
    }

    public static <E> Collection<E> concat(List<Collection<E>> collections) {
        int size = collections.size();
        Collection<E> first = collections.get(0);
        for (int i = 1; i < size; i++) {
            first.addAll(collections.get(i));
        }
        return first;
    }
}
